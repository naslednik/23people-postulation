package cl.ndk.postulation.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomServices<T> {
	public ResponseEntity<?> findAll(Pageable pageable);
	public ResponseEntity<?> findAll();
	public ResponseEntity<?> findById(Integer id);
	public ResponseEntity<?> save(T obj);
	public ResponseEntity<?> delete(Integer id);
}
