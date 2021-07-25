package com.SpringFirst.Controller.rest;



import java.rmi.ServerException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SpringFirst.Model.Actor;
import com.SpringFirst.repository.ActorJpaRepository;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/actors")
public class ActorRestController {
	
	@Autowired
	ActorJpaRepository actorRepo;
	
	@GetMapping
	Iterable<Actor> all() {
		return actorRepo.findAll();
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Actor> getActor(@Parameter(description="Id of the actor.", required=true)
			@PathVariable Long id) throws Exception {
		Optional<Actor> result = this.actorRepo.findById(id);
		
		if(result.isPresent())
		{
			return ResponseEntity.ok().body(result.get());
		}
		else
		{
			return  ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(null);
		}

	}
	
	@PostMapping
	ResponseEntity<Actor> saveActor(@RequestBody Actor actor) {
		Actor newEntity = this.actorRepo.save(actor);
		
		log.info("new Entity" + newEntity);
		
	        return new ResponseEntity<>(newEntity, HttpStatus.CREATED);
	    
	}
		

}
