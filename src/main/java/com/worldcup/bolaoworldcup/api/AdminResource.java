package com.worldcup.bolaoworldcup.api;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worldcup.bolaoworldcup.exception.ResourceNotFoundException;
import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.repository.GameRepository;
import com.worldcup.bolaoworldcup.service.BetUserPointsGameService;
import com.worldcup.bolaoworldcup.service.LeagueClassificaoService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminResource  implements Serializable{



	@Autowired
	GameRepository repository;


	@Autowired
	BetUserPointsGameService pointsService;
	@Autowired
	LeagueClassificaoService leagueClassificaoService;


	@PostMapping("/admin")
	public ResponseEntity<String> getAdminHello() {
		
		
		
		return new  ResponseEntity<>("Hello Dev", HttpStatus.OK);
	}










}