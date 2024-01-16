package com.sousaz.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sousaz.crudspring.model.Course;
import com.sousaz.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController // avisa que vai conter um endpoint, uma url
@RequestMapping("/api/courses") // endpoint
@AllArgsConstructor // cria o construtor com todos argumentos
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping // mapeando o metodo como GET
    public List<Course> list(){
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id){
        return courseRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }


}
