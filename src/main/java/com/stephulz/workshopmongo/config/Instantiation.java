package com.stephulz.workshopmongo.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.stephulz.workshopmongo.domain.User;
import com.stephulz.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(Instantiation.class);

	@Override
	public void run(String... args) throws Exception {

		log.info("LOGGER - CONFIG - Cleaning User collection");
		userRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		log.info("LOGGER - CONFIG - Saving instantiated Users");
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	}

}
