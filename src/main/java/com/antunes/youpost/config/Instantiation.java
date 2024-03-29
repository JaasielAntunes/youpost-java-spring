package com.antunes.youpost.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.antunes.youpost.domain.Post;
import com.antunes.youpost.domain.User;
import com.antunes.youpost.dto.AuthorDTO;
import com.antunes.youpost.dto.ComentDTO;
import com.antunes.youpost.repository.PostRepository;
import com.antunes.youpost.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/01/2022"), "Partiu viagem", "Vou viajar para São Paulo!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("12/01/2022"), "Hoje é dia de comprar roupas", "Vou comprar roupas para minha nova coleção.", new AuthorDTO(maria));	
		
		ComentDTO c1 = new ComentDTO("Boa viagem para você!!", sdf.parse("17/01/2022"), new AuthorDTO(alex));
		ComentDTO c2 = new ComentDTO("Aproveita!", sdf.parse("18/01/2022"), new AuthorDTO(bob));
		ComentDTO c3 = new ComentDTO("Tenha uma ótima semana!", sdf.parse("19/01/2022"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
