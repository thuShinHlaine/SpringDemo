package com.SpringFirst.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.SpringFirst.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	@Autowired
	MovieRepository movieRespository;
	  @GetMapping 
	  public String home() {
		  log.info("Home Controller");
			/*
			 * List<Movie> movies=this.movieRespository.findAll();
			 * log.info("Movies List ="+movies.size());
			 * 
			 * Optional<Movie> movie=this.movieRespository.findById(1L); log.info("Movie1 "+
			 * movie);
			 */
		  return "home"; 
		  }
	 

}
