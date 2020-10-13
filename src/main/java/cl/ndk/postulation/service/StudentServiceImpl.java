package cl.ndk.postulation.service;

import java.util.Optional;
import java.util.StringTokenizer;

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
		if (!rutValidation(student.getRut())) {//valid ruts are 12345678-9, 12.345.678-9
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if (student.getAge()<=18) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(student));
	}

	@Override
	public ResponseEntity<?> delete(Integer id) {
		Optional<Student> opt = repo.findById(id);
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

	
	private boolean rutValidation(String rut) {
		if (!rut.matches("^(\\d{1,2}(((?:\\.\\d{1,3}){2})|(\\d{6}))-[\\dkK])$")) {
			return false;
		}
		StringTokenizer tokenizer = new StringTokenizer(rut, "-");

		String numbers = tokenizer.nextToken();
		char rutDigit = tokenizer.nextToken().charAt(0);
		
		if (numbers.contains(".")) {
			tokenizer = new StringTokenizer(numbers, ".");
			String aux = "";
			while (tokenizer.hasMoreElements()) {
				aux += tokenizer.nextToken();
			}
			numbers = aux;
		}
		
		int sum = 0;
		for (int i = numbers.length()-1; i >= 0; i--) {
			sum += Integer.valueOf(String.valueOf(numbers.charAt(i)))*((numbers.length()-(i+1))%6+2);
		}
		
		char finalChar = 0;
		int digitValue = 11-(sum%11);
		
		if (digitValue == 10) {
			finalChar = 'k';
		}else if(digitValue == 11){
			finalChar = 0;
		}else {
			finalChar = Character.forDigit(digitValue, 10);
		}
		
		return rutDigit == finalChar;
	}
	
}
