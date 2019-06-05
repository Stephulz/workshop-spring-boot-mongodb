package com.stephulz.workshopmongo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephulz.workshopmongo.domain.User;
import com.stephulz.workshopmongo.repository.UserRepository;
import com.stephulz.workshopmongo.resources.UserResource;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(UserResource.class);

	public List<User> findAll() {
		log.info("LOGGER - SERVICE - Getting all Users ");
		return userRepository.findAll();
	}

}
