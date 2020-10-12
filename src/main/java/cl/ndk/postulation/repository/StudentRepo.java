package cl.ndk.postulation.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cl.ndk.postulation.models.Student;

@Repository
public interface StudentRepo extends PagingAndSortingRepository<Student, Integer> {

}
