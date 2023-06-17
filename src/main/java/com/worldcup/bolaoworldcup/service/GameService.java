package com.worldcup.bolaoworldcup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.repository.GameRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GameService {
	
	@Autowired
	GameRepository repository;


	
	
	public Games getGame(Long id) {
		  return  repository.findById(id).orElseThrow();
	}

}
