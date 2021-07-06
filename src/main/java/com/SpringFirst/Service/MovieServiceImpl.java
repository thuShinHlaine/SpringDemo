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
		List<MovieDto> movieDtos = new ArrayList<MovieDto>();
		Iterable<Movie> movies = this.movieRepository.findAll();
		for(Movie movie : movies)
		{
			MovieDto dto = mapper.map(movie, MovieDto.class);
			movieDtos.add(dto);
		}
		
		return movieDtos;
	}

	@Override
	public MovieDto saveMovie(MovieDto movieDto) {
		Movie movie =mapper.map(movieDto, Movie.class);
		movie=this.movieRepository.save(movie);
		return mapper.map(movie, MovieDto.class);
	}

	@Override
	public void deleteMovie(MovieDto movieDto) {
		Movie movie =mapper.map(movieDto, Movie.class);
		this.movieRepository.delete(movie);
		
	}

}
