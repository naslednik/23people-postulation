package cl.ndk.postulation.service;

import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cl.ndk.postulation.models.Student;
import cl.ndk.postulation.repository.StudentRepo;

@Service
public class StudentServiceImpl implements CustomServices<Student> {
	@Autowired
	StudentRepo repo;

	@Override
	public ResponseEntity<?> findAll(Pageable pageable) {
		Page<Student> pagination = repo.findAll(pageable);
		
		return ResponseEntity.ok(pagination);
	}

	@Override
	public ResponseEntity<?> findAll() {
		Iterable<Student>itr = repo.findAll();
		return ResponseEntity.ok(IteratorUtils.toList(itr.iterator()));
	}

	@Override
	public ResponseEntity<?> findById(Integer id) {
		Optional<Student> opt = repo.findById(id);
		if (!opt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(opt.get());
	}

	@Override
	public ResponseEntity<?> save(Student student, String contentType) {
		//Header attribute content-type is not application/json
		if (!contentType.equals("application/json")) {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();			
		}
		
		//
		// TODO validate rut and age.
		//
		return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(student));
	}

	@Override
	public ResponseEntity<?> delete(Integer id) {
		Optional<Student> opt = repo.findById(id);
		if (!opt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}

	
}
