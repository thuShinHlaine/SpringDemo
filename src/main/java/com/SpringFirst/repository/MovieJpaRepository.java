package com.SpringFirst.repository;

import org.springframework.data.repository.CrudRepository;

import com.SpringFirst.Model.Movie;

public interface MovieJpaRepository extends CrudRepository<Movie, Long>{

}
