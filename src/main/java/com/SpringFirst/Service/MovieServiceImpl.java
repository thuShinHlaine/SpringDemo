package com.SpringFirst.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringFirst.Dto.MovieDto;
import com.SpringFirst.Model.Movie;
import com.SpringFirst.repository.MovieJpaRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieJpaRepository movieRepository;

	@Autowired(required = true)
	ModelMapper mapper;

	private List<MovieDto> entityListToDto(Iterable<Movie> movies) {
		List<MovieDto> movieDtos = new ArrayList<MovieDto>();
		for (Movie movie : movies) {
			MovieDto dto = mapper.map(movie, MovieDto.class);
			movieDtos.add(dto);
		}

		return movieDtos;
	}

	@Override
	public List<MovieDto> getAllMovies() {
		// Iterable<Movie> movies = this.movieRepository.findAll();

		Iterable<Movie> movies = this.movieRepository.findAll();
		return entityListToDto(movies);
	}

	@Override
	public MovieDto saveMovie(MovieDto movieDto) {
		Movie movie = mapper.map(movieDto, Movie.class);
		movie = this.movieRepository.save(movie);
		return mapper.map(movie, MovieDto.class);
	}

	@Override
	public MovieDto getMovieById(Long id) {
		Optional<Movie> movieOpt = this.movieRepository.findById(id);
		if (movieOpt.isPresent()) {
			Movie movie = movieOpt.get();
			MovieDto dto = mapper.map(movie, MovieDto.class);

			return dto;
		} else {
			return new MovieDto();
		}

	}

	@Override
	public void deleteMovieById(Long movieId) {
		this.movieRepository.deleteById(movieId);

	}

	@Override
	public List<MovieDto> getMovieByName(String name) {
		Iterable<Movie> movies = this.movieRepository.findByName(name);
		return entityListToDto(movies);
	}

	@Override
	public List<MovieDto> getMovieByNameLike(String name) {
		Iterable<Movie> movies = this.movieRepository.findByNameContaining(name);
		return entityListToDto(movies);
	}

	@Override
	public List<MovieDto> getMovieByNameLikeOne(String name) {
		Iterable<Movie> movies = this.movieRepository.findByNameLike(name);
		return entityListToDto(movies);
	}

	@Override
	public List<MovieDto> getMovieGreaterThanYear(Long year) {
		Iterable<Movie> movies = this.movieRepository.findByYearGreaterThanEqual(year);
		return entityListToDto(movies);
	}

}
