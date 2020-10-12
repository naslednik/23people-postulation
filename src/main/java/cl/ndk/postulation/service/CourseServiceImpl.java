package cl.ndk.postulation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

		return null;
	}

	@Override
	public ResponseEntity<?> findById(Integer id) {

		return null;
	}

	@Override
	public ResponseEntity<?> save(Course obj) {

		return null;
	}

	@Override
	public ResponseEntity<?> delete(Integer id) {

		return null;
	}

	
}
