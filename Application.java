package com.example.demo;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.demo.Student.*;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) { return args -> {
    	
    	Student m = new Student("MJ","Watson","m@email.com", 21);
    	Student p = new Student("Peter","Parker","p@email.com", 25);
    	Student h = new Student("Harry","Osbourne","h@email.com", 21);
    	List<Student> s = new ArrayList<>();
    	s.add(m);s.add(h);s.add(p);
    	studentRepository.saveAll(s);
    	
    };
    }    
}
