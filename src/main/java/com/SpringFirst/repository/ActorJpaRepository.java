package com.SpringFirst.repository;

import org.springframework.data.repository.CrudRepository;
import com.SpringFirst.Model.Actor;

public interface ActorJpaRepository extends CrudRepository<Actor, Long>{
	

}
