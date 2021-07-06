package com.SpringFirst.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String createMovie(@Valid MovieDto movie, Errors errors, @SessionAttribute("cart") ShoppingCart cart,
											Model model) {
		if (errors.hasErrors()) {
			log.info("Post Controller has Error");
			//.addMovie(movie);
			return "movie";
		} else {
			log.info("Post Controller");
			this.movieService.saveMovie(movie);
			//cart.addMovie(movie);
			return "redirect:movie/movielist";
		}

	}

	@GetMapping("/movielist")
	public String emptyMovie(Model model) {
		List<MovieDto> movielist =this.movieService.getAllMovies();
		model.addAttribute("movielist", movielist);

		return "movielist";
	}

}
