package com.SpringFirst.Service;

import java.util.List;

import com.SpringFirst.Dto.MovieDto;

public interface MovieService {
	List<MovieDto> getAllMovies();
	MovieDto saveMovie(MovieDto movie);
	void deleteMovie(MovieDto movie);

}
