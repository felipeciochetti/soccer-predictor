package com.worldcup.bolaoworldcup.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldcup.bolaoworldcup.exception.ResourceNotFoundException;
import com.worldcup.bolaoworldcup.model.BetUserGame;
import com.worldcup.bolaoworldcup.model.BetUserGameKey;
import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.model.Usuarios;
import com.worldcup.bolaoworldcup.repository.BetUserGameRepository;
import com.worldcup.bolaoworldcup.repository.GameRepository;
import com.worldcup.bolaoworldcup.repository.UsuarioRepository;
import com.worldcup.bolaoworldcup.view.BetUserGameView;



@Service
public class BetUserGameService   implements Serializable{


	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BetUserGameService.class);
	
	

	@Autowired
	BetUserGameRepository repository;
	@Autowired
	UsuarioRepository userRepository;
	@Autowired
	GameRepository gameRepository;




	public BetUserGameService() {
	}



	public BetUserGame adicionar(Long userId, BetUserGameView bet) {

		
		BetUserGame betObj  = new BetUserGame();
		
		Usuarios user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User: " + userId));
		Games game = gameRepository.findById(bet.getGameId()).orElseThrow(() -> new ResourceNotFoundException("game: " + bet.getGameId()));
		
		
		BetUserGameKey fk = new BetUserGameKey();
		fk.setGameId(game.getId());
		fk.setUserId(user.getId());
		betObj.setId(fk);
		betObj.setGame(game);
		betObj.setUser(user);
		betObj.setScoreHome(bet.getScoreHome());
		betObj.setScoreVisitor(bet.getScoreVisitor());
		betObj.setDate(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime());
		
		return repository.save(betObj);



	}

	

}
