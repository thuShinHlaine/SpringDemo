package com.SpringFirst.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.SpringFirst.Model.Movie;

public interface MovieJpaRepository extends CrudRepository<Movie, Long>{
	List<Movie> findByName(String name);
	List<Movie> findByNameLike(String name);
	List<Movie> findByNameContaining(String name);
	List<Movie> findByYearGreaterThanEqual(Long year);
	
	//SQL
	@Query(value="SELECT * FROM movie ORDER BY director",
			nativeQuery = true)
	List<Movie> getAllMovie();
	
	//JPQL
	@Query(value="SELECT m FROM Movie m")
	List<Movie> getAllMovieByJpql();
	

}
