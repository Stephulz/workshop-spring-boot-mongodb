package com.stephulz.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stephulz.workshopmongo.domain.Post;
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
		log.info("LOGGER - USER RESOURCE - Getting all Users");
		List<User> list = userService.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping
	@RequestMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		log.info("LOGGER - USER RESOURCE - Getting User by ID: " + id);
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO userDTO) {
		log.info("LOGGER - USER RESOURCE - Inserting new User");
		User user = userService.fromDTO(userDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		userService.insert(user);
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		log.info("LOGGER - USER RESOURCE - Deleting User by ID: " + id);
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody UserDTO userDTO, @PathVariable String id) {
		log.info("LOGGER - USER RESOURCE - Updating User: " + id);
		User user = userService.fromDTO(userDTO);
		user.setId(id);
		user = userService.update(user);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	@RequestMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		log.info("LOGGER - USER RESOURCE - Getting all User Posts");
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user.getPosts());
	}

}
