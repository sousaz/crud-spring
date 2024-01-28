package com.sousaz.crudspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.sousaz.crudspring.dto.CourseDTO;
import com.sousaz.crudspring.dto.CoursePageDTO;
import com.sousaz.crudspring.dto.mapper.CourseMapper;
import com.sousaz.crudspring.exception.RecordNotFoundException;
import com.sousaz.crudspring.model.Course;
import com.sousaz.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper){
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    // public List<CourseDTO> list(){
    //     return courseRepository.findAll()
    //         .stream()
    //         .map(courseMapper::toDTO)
    //         .collect(Collectors.toList());
    // }

    public CoursePageDTO list(@PositiveOrZero int pageNumber, @Positive @Max(100) int pageSize){
        Page<Course> page = courseRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<CourseDTO> courses = page.get().map(courseMapper::toDTO)
            .collect(Collectors.toList());
        return new CoursePageDTO(courses, page.getTotalElements(), page.getTotalPages());
    }

    public CourseDTO findById(@NotNull @Positive Long id){
        return courseRepository.findById(id)
            .map(courseMapper::toDTO)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course){
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid CourseDTO courseDTO){
        return courseRepository.findById(id)
            .map(recordFound -> {
                Course course = courseMapper.toEntity(courseDTO);
                recordFound.setName(courseDTO.name());
                recordFound.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));
                recordFound.getLessons().clear();
                course.getLessons().forEach(lesson -> recordFound.getLessons().add(lesson));
                return courseMapper.toDTO(courseRepository.save(recordFound));
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id){
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
