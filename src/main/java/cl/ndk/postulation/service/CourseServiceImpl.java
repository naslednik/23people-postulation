package cl.ndk.postulation.service;

import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cl.ndk.postulation.models.Course;
import cl.ndk.postulation.repository.CourseRepo;

@Service
public class CourseServiceImpl implements CustomServices<Course> {
	@Autowired
	CourseRepo repo;

	@Override
	public ResponseEntity<?> findAll(Pageable pageable) {
		Page<Course> pagination = repo.findAll(pageable);
		
		return ResponseEntity.ok(pagination);
	}

	@Override
	public ResponseEntity<?> findAll() {
		Iterable<Course>itr = repo.findAll();
		return ResponseEntity.ok(IteratorUtils.toList(itr.iterator()));
	}

	@Override
	public ResponseEntity<?> findById(Integer id) {
		Optional<Course> opt = repo.findById(id);
		if (!opt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(opt.get());
	}

	@Override
	public ResponseEntity<?> save(Course course, String contentType) {
		if (!contentType.equals("application/json")) {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();			
		}
		if (course.getCode().length()>4) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		repo.save(course);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Override
	public ResponseEntity<?> delete(Integer id) {
		Optional<Course> opt = repo.findById(id);
		if (!opt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		try {
			repo.deleteById(id);			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	
}
