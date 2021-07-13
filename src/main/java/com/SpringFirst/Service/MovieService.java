package com.SpringFirst.Service;

import java.util.List;

import com.SpringFirst.Dto.MovieDto;

public interface MovieService {
	List<MovieDto> getAllMovies();
	List<MovieDto> getMovieByName(String  name);
	List<MovieDto> getMovieByNameLike(String  name);
	 MovieDto getMovieById(Long id);
	MovieDto saveMovie(MovieDto movie);
	void deleteMovieById(Long movieId);

}
