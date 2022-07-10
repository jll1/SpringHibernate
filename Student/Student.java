package com.example.demo.Student;
import javax.persistence.*; 

@Entity(name = "Student")
@Table(name = "student", uniqueConstraints={@UniqueConstraint(name = "student_email_unique", columnNames = "email")})//change unique constraint name
public class Student {
	
    @Id// make field a primary key
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(generator = "student_sequence")
    @Column(name="id", updatable=false)
    private Long id;  
    
    @Column(nullable=false, columnDefinition="TEXT")
	private String firstName;
    
    @Column(nullable=false, columnDefinition="TEXT")
	private String lastName;
    
    @Column(nullable=false, columnDefinition="TEXT")
	private String email;
    
    @Column(nullable=false)
	private Integer age;
	
	
	public Student() {super();}

	public Student(String firstName, String lastName, String email, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", age=" + age + "]";
	}
	
	
}
