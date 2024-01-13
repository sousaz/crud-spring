package com.sousaz.crudspring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
}
