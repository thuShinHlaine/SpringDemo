package com.SpringFirst.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.SpringFirst.Dto.MovieDto;

import com.SpringFirst.Model.ShoppingCart;
import com.SpringFirst.Service.MovieService;



import lombok.extern.slf4j.Slf4j;

@Valid
@SessionAttributes("cart")
@Slf4j
@Controller
@RequestMapping("/movie")
public class MovieController {
	/*
	 * @Autowired MovieJpaRepository movieRepository;
	 */
	
	@Autowired
	MovieService movieService;

	@ModelAttribute("cart")
	public ShoppingCart getCart() {
		log.info("Create new Shopping Cart");
		return new ShoppingCart();
	}


	@GetMapping
	public String movie(Model model) {
		log.info("GetMovie");
		MovieDto movie = new MovieDto();
		model.addAttribute("movie", movie);
		return "movie";
	}

	@PostMapping
	public String createMovie(@Valid @ModelAttribute("movie") MovieDto movie, Errors errors, @SessionAttribute("cart") ShoppingCart cart) {
		if (errors.hasErrors()) {
			log.info("Post Controller has Error");
			
			return "movie";
		} else {
			log.info("Post Controller");
			this.movieService.saveMovie(movie);
			
			return "redirect:movie/movielist";
		}

	}

	@GetMapping("/movielist")
	public String emptyMovie(Model model) {
		List<MovieDto> movielist =this.movieService.getAllMovies();
		model.addAttribute("movielist", movielist);
		return "movielist";
	}
	
	@GetMapping("update/{movieId}")
	public String updateMovie(@PathVariable Long movieId ,Model model) {
		log.info("Update Movie "+movieId);
		MovieDto movie = this.movieService.getMovieById(movieId);
		model.addAttribute("movie",movie);
		return "movie";
	}
	
	@GetMapping("delete/{movieId}")
	public String deleteMovie(@PathVariable Long movieId ) {
		log.info("Delete Movie "+movieId);
		this.movieService.deleteMovieById(movieId);
		return "redirect:/movie/movielist";
	}
	
	@GetMapping("search")
	public String findByName(@RequestParam String name,Model model ) {
		log.info("Search Movie "+name);
		List<MovieDto> movies = this.movieService.getMovieByName(name);
		for(MovieDto dto: movies) {
			log.info(dto.toString());
		}
		model.addAttribute("movielist", movies);
		return "movielist";
	}
	
	@GetMapping("search-like-containing")
	public String findByNameLike(@RequestParam String name,Model model ) {
		log.info("Search Movie "+name);
		List<MovieDto> movies = this.movieService.getMovieByNameLike(name);
		for(MovieDto dto: movies) {
			log.info(dto.toString());
		}
		model.addAttribute("movielist", movies);
		return "movielist";
	}
	
	@GetMapping("search-like")
	public String findByNameLikeOne(@RequestParam String name,Model model ) {
		log.info("Search Movie "+name);
		List<MovieDto> movies = this.movieService.getMovieByNameLikeOne("%"+name+"%");
		for(MovieDto dto: movies) {
			log.info(dto.toString());
		}
		model.addAttribute("movielist", movies);
		return "movielist";
	}
	
	@GetMapping("search-greater-than")
	public String findByYearGreaterThan(@RequestParam Long year,Model model ) {
		log.info("Search Year Greater Than Movie "+year);
		List<MovieDto> movies = this.movieService.getMovieGreaterThanYear(year);
		for(MovieDto dto: movies) {
			log.info(dto.toString());
		}
		model.addAttribute("movielist", movies);
		return "movielist";
	}

}
