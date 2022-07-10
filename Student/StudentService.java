package com.example.demo.Student;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	private final StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	public Optional<Student> findStudentById (Long id){
		 return studentRepository.findById(id);
	}
	
	public List<Student> deleteStudent(Long id) {
		boolean exists = studentRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException("Id not found");
		}else {
			studentRepository.deleteById(id);
		}
        return studentRepository.findAll();
	}
	
	public Student addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent()) {
    		throw new IllegalStateException("Email taken");
    	}
		studentRepository.save(student);
    	return student;
	}
	
	@Transactional//rollback on runtime exceptions
	public void updateStudent(Student student) {
		Student s = studentRepository.findById(student.getId()).orElseThrow(() -> new IllegalStateException("Id does not exist"));
		
		if(s.getFirstName() != null && s.getFirstName().length()>0 && !Objects.equals(s.getFirstName(),student.getFirstName())) {
			s.setFirstName(student.getFirstName());
		}
		
		if(s.getLastName() != null && s.getLastName().length()>0 && !Objects.equals(s.getLastName(),student.getLastName())) {
			s.setLastName(student.getLastName());
		}
		
		if(s.getEmail() != null && s.getEmail().length()>0 && !Objects.equals(s.getEmail(),student.getEmail())) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(s.getEmail());
			if(!studentOptional.isPresent()) {
	    		throw new IllegalStateException("Email taken");
	    	}
			s.setEmail(student.getEmail());
		}
		
		if(s.getAge() != null && s.getAge()>0 && s.getAge()!= s.getAge()) {
			s.setAge(student.getAge());
		}
		
		s.setId(student.getId());
		studentRepository.save(s);		
	}
	


}
