package com.stephulz.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephulz.workshopmongo.domain.User;
import com.stephulz.workshopmongo.repository.UserRepository;
import com.stephulz.workshopmongo.resources.UserResource;
import com.stephulz.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(UserResource.class);

	public List<User> findAll() {
		log.info("LOGGER - SERVICE - Getting all Users ");
		return userRepository.findAll();
	}

	public User findById(String id) {
		log.info("LOGGER - SERVICE - Getting User by ID: " + id);
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

}
