package com.stephulz.workshopmongo.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stephulz.workshopmongo.domain.User;
import com.stephulz.workshopmongo.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	UserService userService;

	private static final Logger log = LoggerFactory.getLogger(UserResource.class);

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		log.info("LOGGER - RESOURCE - Getting all Users");
		List<User> users = userService.findAll();
		return ResponseEntity.ok().body(users);
	}

}
