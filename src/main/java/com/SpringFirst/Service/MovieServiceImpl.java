package com.SpringFirst.Service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringFirst.Dto.MovieDto;
import com.SpringFirst.Model.Movie;
import com.SpringFirst.repository.MovieJpaRepository;
@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieJpaRepository movieRepository;
	
	@Autowired(required = true)
	ModelMapper mapper;

	@Override
	public List<MovieDto> getAllMovies() {
		Iterable<Movie> movies = this.movieRepository.findAll();
		return entityListToDto(movies);
	}

	@Override
	public MovieDto saveMovie(MovieDto movieDto) {
		Movie movie =mapper.map(movieDto, Movie.class);
		movie=this.movieRepository.save(movie);
		return mapper.map(movie, MovieDto.class);
	}

	@Override
	public MovieDto getMovieById(Long id) {
		Movie movie = this.movieRepository.findById(id).get();
		MovieDto dto = mapper.map(movie, MovieDto.class);
		
		return dto;
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


	private List<MovieDto> entityListToDto(Iterable<Movie> movies) {
		List<MovieDto> movieDtos = new ArrayList<MovieDto>();		
		for(Movie movie : movies)
		{
			MovieDto dto = mapper.map(movie, MovieDto.class);
			movieDtos.add(dto);
		}
		
		return movieDtos;
	}

	
	
}
