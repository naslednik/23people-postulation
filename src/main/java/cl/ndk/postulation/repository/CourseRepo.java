package cl.ndk.postulation.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cl.ndk.postulation.models.Course;

@Repository
public interface CourseRepo extends PagingAndSortingRepository<Course, Integer> {

}
