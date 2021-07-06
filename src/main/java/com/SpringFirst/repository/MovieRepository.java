package com.SpringFirst.repository;

import java.util.List;
import java.util.Optional;

import com.SpringFirst.Model.Movie;

public interface MovieRepository {
	List<Movie> findAll();
	
	Optional<Movie> findById(long id);
	
	Movie save(Movie movie);

}
