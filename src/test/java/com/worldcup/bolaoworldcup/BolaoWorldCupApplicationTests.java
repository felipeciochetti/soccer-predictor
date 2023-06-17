package com.worldcup.bolaoworldcup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.worldcup.bolaoworldcup.model.Usuarios;
import com.worldcup.bolaoworldcup.repository.UsuarioRepository;

@SpringBootTest
class BolaoWorldCupApplicationTests {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	JdbcTemplate jdbc;

	@Test
	void contextLoads() {
		
		
	}
	
	
	@Test
	void createUser() {
		
	//	user.setEmail("user@tdd.com");
	//	user.setName("TDD");
		
		
		// Optional<Usuarios> usuario = repository.findByEmail(user.getEmail());
		//Optional.ofNullable(user).ifPresent(el -> System.out.println(el.getEmail()));
		
		//assertNotEquals("xx", Optional.of(user).ifPresent(el -> el.getEmail()));
		
		
	}

}
