package com.sousaz.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sousaz.crudspring.enums.Category;
import com.sousaz.crudspring.model.Course;
import com.sousaz.crudspring.model.Lesson;
import com.sousaz.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}
   
 	@Bean // controla o ciclo de vida
	CommandLineRunner initDatabase(CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();

			for(int i = 0; i < 20; i++){
				Course c = new Course();
				c.setName("Angular com spring");
				c.setCategory(Category.FRONTEND);
	
				Lesson l = new Lesson();
				l.setName("Introdução");
				l.setYoutubeUrl("v1//:watch");
				l.setCourse(c);
				c.getLessons().add(l);
				Lesson l1 = new Lesson();
				l1.setName("final");
				l1.setYoutubeUrl("v2//:watch");
				l1.setCourse(c);
				c.getLessons().add(l1);
				courseRepository.save(c);
			}
		};
	}

}
