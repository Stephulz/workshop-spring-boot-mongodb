package com.stephulz.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephulz.workshopmongo.domain.User;
import com.stephulz.workshopmongo.dto.UserDTO;
import com.stephulz.workshopmongo.repository.UserRepository;
import com.stephulz.workshopmongo.resources.UserResource;
import com.stephulz.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(UserResource.class);

	public List<User> findAll() {
		log.info("LOGGER - USER SERVICE - Getting all Users ");
		return userRepository.findAll();
	}

	public User findById(String id) {
		log.info("LOGGER - USER SERVICE - Getting User by ID: " + id);
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

	public User insert(User user) {
		log.info("LOGGER - USER SERVICE - Inserting new User");
		return userRepository.insert(user);
	}

	public void deleteById(String id) {
		log.info("LOGGER - USER SERVICE - Getting User by ID to delete: " + id);
		Optional<User> obj = userRepository.findById(id);
		if (obj != null) {
			log.info("LOGGER - USER SERVICE - Deleting User by ID: " + id);
			userRepository.deleteById(id);
		} else {
			new ObjectNotFoundException("Object not found");
		}
	}

	public User update(User user) {
		log.info("LOGGER - USER SERVICE - Updating User: " + user.getId());
		Optional<User> newUser = userRepository.findById(user.getId());
		updateData(newUser.get(), user);
		return userRepository.save(newUser.get());
	}

	public void updateData(User newUser, User user) {
		log.info("LOGGER - USER SERVICE - Updating User - Copying data to a new object in order to save it");
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
	}

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
}
