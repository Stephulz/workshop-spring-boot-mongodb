package com.stephulz.workshopmongo.resources;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stephulz.workshopmongo.domain.Post;
import com.stephulz.workshopmongo.resources.util.URL;
import com.stephulz.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	PostService postService;

	private static final Logger log = LoggerFactory.getLogger(PostResource.class);

	@GetMapping
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		log.info("LOGGER - POST RESOURCE - Getting Post by ID: " + id);
		Post post = postService.findById(id);
		return ResponseEntity.ok().body(post);
	}

	@GetMapping
	@RequestMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@Valid @RequestParam(value = "text", defaultValue = "") String text) {
		log.info("LOGGER - POST RESOURCE - Search Posts by Title");
		text = URL.decodeParam(text);
		List<Post> list = postService.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
}