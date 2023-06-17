package com.worldcup.bolaoworldcup.api;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

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

import com.worldcup.bolaoworldcup.model.BetUserGame;
import com.worldcup.bolaoworldcup.repository.BetUserGameRepository;
import com.worldcup.bolaoworldcup.repository.UsuarioRepository;
import com.worldcup.bolaoworldcup.service.BetUserGameService;
import com.worldcup.bolaoworldcup.service.ViewPointService;
import com.worldcup.bolaoworldcup.view.BetUserGameView;
import com.worldcup.bolaoworldcup.view.ViewPointView;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BetUserGameResource  implements Serializable{



	@Autowired
	BetUserGameRepository repository;
	
	@Autowired
	UsuarioRepository userRepository;
	
	@Autowired
	BetUserGameService service;
	
	@Autowired
	ViewPointService viewService;

	@GetMapping("/user/{userId}/palpites")
	public ResponseEntity<List<BetUserGame>> getAllBetByUserGames(@PathParam(value = "userId") Long userId) {
		List<BetUserGame> Gamess =  repository.findAllByIdUserId(userId);
		return new  ResponseEntity<>(Gamess, HttpStatus.OK);
	}
	
	
	@GetMapping("/user/{usuarioId}/palpite/{gameId}")
	public ResponseEntity<BetUserGame> getBetByUserAndGames(@PathVariable(value = "usuarioId") Long usuarioId,@PathVariable(value = "gameId") Long gameId) {
		
		
		BetUserGame Gamess =  repository.findAllByIdUserIdAndGameId(usuarioId,gameId).orElse(null);
		
		
		return new  ResponseEntity<>(Gamess, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/user/{userId}/palpites")
	public ResponseEntity<BetUserGame> createBet(@PathVariable("userId") Long userId, @RequestBody BetUserGameView bet) {

		BetUserGame new_course = service.adicionar(userId,bet);

		System.out.println("palpite salvo");
		return new ResponseEntity<>(new_course, HttpStatus.CREATED);

	}

	

	@GetMapping("/user/{userId}/view-points")
	public ResponseEntity<List<ViewPointView>> getViewPointsByUser(@PathVariable(value = "userId") Long userId ,@RequestParam(required= false , value= "week" ) Optional<Long> week  ) {
		
		return new  ResponseEntity<>(viewService.getViewPoints(userId,week), HttpStatus.OK);
	}
	







}