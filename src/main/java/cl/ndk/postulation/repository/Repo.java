package cl.ndk.postulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 	CourseRepo extends JpaRepository due to that interface 
 *  extends PagingAndSortingRepository, a usefull interface
 *  to implement pagination on our project.
 * */
public interface Repo<T> extends JpaRepository<T, Integer> {

}
