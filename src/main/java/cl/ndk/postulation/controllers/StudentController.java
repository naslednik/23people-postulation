package cl.ndk.postulation.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.ndk.postulation.models.Student;
import cl.ndk.postulation.service.StudentServiceImpl;

@RestController
@RequestMapping("/")
public class StudentController {
	@Autowired
	StudentServiceImpl studentService;
	
	@RequestMapping(method = RequestMethod.GET, path = "GET/students")
	public ResponseEntity<?> getPaginatedCourses(){
		Pageable pageable = Pageable.unpaged();
		return studentService.findAll(pageable);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "GET/students/all")
	public ResponseEntity<?> getAllCourses(){
		return studentService.findAll();
	}
	@RequestMapping(method = RequestMethod.GET, path = "GET/students/{id}")
	public ResponseEntity<?> getCourseByID(@PathVariable int id){
		return studentService.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "POST/students")
	public ResponseEntity<?> createCourse(@RequestBody Student student, @RequestHeader("Content-Type") String contentType){
		return studentService.save(student, contentType);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "PUT/students/{id}")
	public ResponseEntity<?> updateCourse(@RequestBody Student student, @RequestHeader("Content-Type") String contentType, @PathVariable int id){
		student.setId(id);
		return studentService.save(student, contentType);
	}
	@RequestMapping(method = RequestMethod.DELETE, path = "DELETE/students/{id}")
	public ResponseEntity<?> deleteCourse( @PathVariable int id){
		return studentService.delete(id);
	}
}
