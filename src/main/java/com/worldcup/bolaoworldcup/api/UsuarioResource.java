package com.worldcup.bolaoworldcup.api;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worldcup.bolaoworldcup.exception.ResourceNotFoundException;
import com.worldcup.bolaoworldcup.model.BetUserGame;
import com.worldcup.bolaoworldcup.model.League;
import com.worldcup.bolaoworldcup.model.LeagueClassificacao;
import com.worldcup.bolaoworldcup.model.Usuarios;
import com.worldcup.bolaoworldcup.repository.LeagueClassificacaoRepository;
import com.worldcup.bolaoworldcup.repository.UsuarioRepository;
import com.worldcup.bolaoworldcup.service.LeagueClassificaoService;
import com.worldcup.bolaoworldcup.view.BetUserGameView;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioResource  implements Serializable{



	@Autowired
	UsuarioRepository repository;

	@Autowired
	LeagueClassificacaoRepository leagueRepository;

	@Autowired
	LeagueClassificaoService service;

	@GetMapping("/users")
	public ResponseEntity<List<Usuarios>> getAllUsuarios() {

		List<Usuarios> Gamess = new ArrayList<Usuarios>();

		repository.findAll().forEach(Gamess::add);


		return new  ResponseEntity<>(Gamess, HttpStatus.OK);
	}



	@PostMapping("/user")
	public ResponseEntity<Usuarios> createUser(@RequestBody Usuarios usuario) {


		Optional<Usuarios> check =  repository.findByEmail(usuario.getEmail());

		check = Optional.of(repository.save(usuario));

		return new ResponseEntity<>(check.get(), HttpStatus.CREATED);
	}

	
	@GetMapping("/user/{email}")
	public ResponseEntity<Usuarios> getUserByEmail(@PathVariable("email") String email) {

		Usuarios user =  repository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User: " + email));


		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}


	@GetMapping("/user")
	public ResponseEntity<List<Usuarios>> getUserSearchByEmail(@RequestParam(required = false) String email) {

		List<Usuarios> usuario = repository.findByEmailLike("%" + email + "%");
		
		
		
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}




	






}