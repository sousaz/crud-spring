package com.sousaz.crudspring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sousaz.crudspring.model.Course;
import com.sousaz.crudspring.repository.CourseRepository;

@RestController // avisa que vai conter um endpoint, uma url
@RequestMapping("/api/courses") // endpoint
public class CourseController {

    private CourseRepository courseRepository;

    @GetMapping // mapeando o metodo como GET
    public List<Course> list(){
        return null;
    }
}
