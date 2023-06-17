package com.worldcup.bolaoworldcup.api;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worldcup.bolaoworldcup.repository.BetUserGamePointsRepository;
import com.worldcup.bolaoworldcup.service.BetUserPointsGameService;
import com.worldcup.bolaoworldcup.view.UserPointsView;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserPointsResource  implements Serializable{



	@Autowired
	BetUserPointsGameService service;
	



	
	
	@GetMapping("/user/points")
	public ResponseEntity<List<UserPointsView>> getAllBet() {
	
		
		return new  ResponseEntity<>(service.getAllBet(), HttpStatus.OK);
	}
	
	


	







}