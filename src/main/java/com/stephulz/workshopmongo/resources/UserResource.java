package com.stephulz.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stephulz.workshopmongo.domain.User;
import com.stephulz.workshopmongo.dto.UserDTO;
import com.stephulz.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	UserService userService;

	private static final Logger log = LoggerFactory.getLogger(UserResource.class);

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		log.info("LOGGER - RESOURCE - Getting all Users");
		List<User> list = userService.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping
	@RequestMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		log.info("LOGGER - RESOURCE - Getting User by ID: " + id);
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

}
