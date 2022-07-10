package com.example.demo.Student;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
	
	@Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }
    
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }
    
    @GetMapping("/students/{id}")
    public Optional<Student> getStudents(@PathVariable Long id) {
        return studentService.findStudentById(id);
    }
    
    @GetMapping("/students/delete/{id}")public List<Student> deleteStudent(@PathVariable Long id) {
    	studentService.deleteStudent(id);  
    	return studentService.getStudents();
    }
    
    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) throws URISyntaxException {
    	Student savedStudent = studentService.addNewStudent(student);
        return ResponseEntity.created(new URI("/" + savedStudent.getId())).body(savedStudent);
    }
    
    @RequestMapping(value="/students/{id}", method=RequestMethod.PUT)
	@PutMapping("/{id}")//update student
    public ResponseEntity updateStudent(@PathVariable(required = true) Long id, @RequestBody(required = true) Student s){
    	
        studentService.updateStudent(s);

        return ResponseEntity.ok(studentService.findStudentById(id));
    }
}
