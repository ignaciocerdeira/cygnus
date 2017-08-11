package com.atsistemas.cygnus.services.user;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.atsistemas.cygnus.services.user.model.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

	// select * from user where name = :n
	@RestResource(path = "by-name")
	Collection<User> findAllByName(@Param("name") String name);

}
