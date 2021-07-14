package com.SpringFirst.Controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.SpringFirst.Dto.MovieDto;
import com.SpringFirst.Service.MovieService;

@RequestMapping("/api/movies")
@RestController
public class MovieRestController {
	@Autowired
	MovieService movieService;

	@GetMapping
	List<MovieDto> all() {
		return movieService.getAllMovies();
	}

	@GetMapping("/{id}")
	ResponseEntity<MovieDto> getMovie(@PathVariable Long id) {
		MovieDto movie = this.movieService.getMovieById(id);
		if (movie.getId() != null) {
			return ResponseEntity.ok().body(movie);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(movie);
		}

	}
	private ResponseEntity<MovieDto> saveOrUpdateMovie(MovieDto movie, BindingResult result) {
		if (!result.hasErrors()) {
			MovieDto resultMovie = this.movieService.saveMovie(movie);
			return ResponseEntity.ok().body(resultMovie);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(movie);
		}
	}

	@PostMapping
	ResponseEntity<MovieDto> saveMovie(@PathVariable Long id,
			@Valid @RequestBody MovieDto movie, 
			BindingResult result) {
		return saveOrUpdateMovie(movie, result);
	}

	@PutMapping("/{id}")
	ResponseEntity<MovieDto> updateMovie(@Valid @RequestBody MovieDto movie, BindingResult result) {
		return saveOrUpdateMovie(movie, result);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<MovieDto> deleteMovie(@PathVariable Long id) {
		MovieDto movie = this.movieService.getMovieById(id);
		if (movie.getId() != null) {
			this.movieService.deleteMovieById(movie.getId());
			return ResponseEntity.ok().body(movie);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(movie);
		}

	}
}
