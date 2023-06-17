package com.worldcup.bolaoworldcup.api;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worldcup.bolaoworldcup.exception.ResourceNotFoundException;
import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.repository.GameRepository;
import com.worldcup.bolaoworldcup.service.BetUserPointsGameService;
import com.worldcup.bolaoworldcup.service.LeagueClassificaoService;


@RestController
@CrossOrigin(origins = "http://localhost:4200"  )
public class GameResource  implements Serializable{



	@Autowired
	GameRepository repository;


	@Autowired
	BetUserPointsGameService pointsService;
	@Autowired
	LeagueClassificaoService leagueClassificaoService;


	@GetMapping("/games")
	public ResponseEntity<List<Games>> getAllGames() {

		List<Games> games = new ArrayList<Games>();

		repository.findAll().forEach(games::add);

		games.sort(Comparator.comparing(Games::getId));
		return new  ResponseEntity<>(games, HttpStatus.OK);
	}
	@GetMapping("/games/{id}")
	public ResponseEntity<Games> getGamesFilteryId(@PathVariable Long id) {

		
		  Games game = repository.findById(id).orElseThrow();

		return new  ResponseEntity<>(game, HttpStatus.OK);
	}
	@GetMapping("/match-week/{week}")
	public ResponseEntity<List<Games>> getGamesFilterByMatchWeek(@PathVariable Long week) {
		
		
		System.out.println("match-week > " + week);
		
		List<Games> games = new ArrayList<Games>();
		repository.findAllByRound(week).forEach(games::add);

		return new  ResponseEntity<>(games, HttpStatus.OK);
	}
	
	

	@PutMapping("/game/{id}")
	public ResponseEntity<Games> updateGame(@PathVariable("id") long id,@RequestParam long scoreHome , @RequestParam long scoreVisitor ) throws Exception {


		Games game = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("game: " + id));
		
		

		game.setScoreHome(scoreHome);
		game.setScoreVisitor(scoreVisitor);

		game = repository.save(game);
		
		
		pointsService.betUserBetGameService(game);
		
		leagueClassificaoService.leagueClassificaoService();
		

		return new ResponseEntity<>(HttpStatus.OK);

	}











}