package cl.ndk.postulation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomServices<T> {
	public Page<T> findAll(Pageable pageable);
	public List<T> findAll();
	public Optional<T> findById(Integer id);
	public T save(T obj);
	public void delete(Integer id);
}
