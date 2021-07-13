package com.SpringFirst.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.SpringFirst.Model.Movie;

public interface MovieJpaRepository extends CrudRepository<Movie, Long>{
	List<Movie> findByName(String name);
	List<Movie> findByNameLike(String name);
	List<Movie> findByNameContaining(String name);
	List<Movie> findByYearGreaterThanEqual(Long year);
	

}
