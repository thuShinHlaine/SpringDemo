package com.SpringFirst.Service;

import java.util.List;

import com.SpringFirst.Dto.MovieDto;

public interface MovieService {
	List<MovieDto> getAllMovies();
	List<MovieDto> getMovieByName(String  name);
	List<MovieDto> getMovieByNameLike(String  name);
	List<MovieDto> getMovieByNameLikeOne(String  name);
	List<MovieDto> getMovieGreaterThanYear(Long  year);
	MovieDto getMovieById(Long id);
	MovieDto saveMovie(MovieDto movie);
	void deleteMovieById(Long movieId);

}
