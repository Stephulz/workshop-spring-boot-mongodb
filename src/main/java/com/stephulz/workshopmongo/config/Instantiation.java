package com.stephulz.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.stephulz.workshopmongo.domain.Post;
import com.stephulz.workshopmongo.domain.User;
import com.stephulz.workshopmongo.repository.PostRepository;
import com.stephulz.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	private static final Logger log = LoggerFactory.getLogger(Instantiation.class);

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		log.info("LOGGER - CONFIG - Cleaning User collection");
		userRepository.deleteAll();
		log.info("LOGGER - CONFIG - Cleaning Post collection");
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Going for a trip", "I'm going to São Paulo, see ya!", maria);
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Good morning", "Today i woke up happy!", maria);
				
		log.info("LOGGER - CONFIG - Saving instantiated Users");
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		log.info("LOGGER - CONFIG - Saving instantiated Posts");
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
