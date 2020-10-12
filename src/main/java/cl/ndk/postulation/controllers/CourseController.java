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

import cl.ndk.postulation.models.Course;
import cl.ndk.postulation.service.CourseServiceImpl;

@RestController
@RequestMapping("/")
public class CourseController {
	@Autowired
	CourseServiceImpl courseService;
	
	@RequestMapping(method = RequestMethod.GET, path = "GET/courses")
	public ResponseEntity<?> getPaginatedCourses(){
		Pageable pageable = Pageable.unpaged();
		return courseService.findAll(pageable);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "GET/courses/all")
	public ResponseEntity<?> getAllCourses(){
		return courseService.findAll();
	}
	@RequestMapping(method = RequestMethod.GET, path = "GET/courses/{id}")
	public ResponseEntity<?> getCourseByID(@PathVariable int id){
		return courseService.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "POST/courses")
	public ResponseEntity<?> createCourse(@va@RequestBody Course course, @RequestHeader("Content-Type") String contentType){
		return courseService.save(course, contentType);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "PUT/courses/{id}")
	public ResponseEntity<?> updateCourse(@RequestBody Course course, @RequestHeader("Content-Type") String contentType, @PathVariable int id){
		course.setId(id);
		return courseService.save(course, contentType);
	}
	@RequestMapping(method = RequestMethod.DELETE, path = "DELETE/courses/{id}")
	public ResponseEntity<?> deleteCourse( @PathVariable int id){
		return courseService.delete(id);
	}
}
