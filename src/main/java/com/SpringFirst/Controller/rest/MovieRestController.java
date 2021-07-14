package com.SpringFirst.Controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
