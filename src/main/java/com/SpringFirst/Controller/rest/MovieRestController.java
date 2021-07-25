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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {
	@Autowired
	MovieService movieService;
	
    @Operation(summary = "Find all movie",description = "Get All Movies",tags= {"movie"})
	@GetMapping
	List<MovieDto> all() {
		return movieService.getAllMovies();
	}
    @Operation(summary = "Get a movie", description = "Get movie by Id", tags = { "movie" })
    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "successful fetch a movie"),
	        @ApiResponse(responseCode = "404", description = "movie not found")
	        })
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
	
	@Operation(summary = "Save a Movie", description = "Save", tags = { "movie" })
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "successful create a movie",
	        		content = @Content(schema = @Schema(implementation = MovieDto.class))),
	        @ApiResponse(responseCode = "400", description = "Validation error")
	        })
	@PostMapping
	ResponseEntity<MovieDto> saveMovie(@PathVariable Long id,
			@Valid @RequestBody MovieDto movie, 
			BindingResult result) {
		return saveOrUpdateMovie(movie, result);
	}

	@Operation(summary = "Update a Movie", description = "Update", tags = { "movie" })
	@PutMapping("/{id}")
	ResponseEntity<MovieDto> updateMovie(@Valid @RequestBody MovieDto movie, BindingResult result) {
		return saveOrUpdateMovie(movie, result);
	}
	
	@Operation(summary = "Delete a Movie", description = "Delete", tags = { "movie" })
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
