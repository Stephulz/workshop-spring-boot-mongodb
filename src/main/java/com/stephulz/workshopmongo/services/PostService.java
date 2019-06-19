package com.stephulz.workshopmongo.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephulz.workshopmongo.domain.Post;
import com.stephulz.workshopmongo.repository.PostRepository;
import com.stephulz.workshopmongo.resources.UserResource;
import com.stephulz.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;

	private static final Logger log = LoggerFactory.getLogger(UserResource.class);

	public Post findById(String id) {
		log.info("LOGGER - USER SERVICE - Getting User by ID: " + id);
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
}
