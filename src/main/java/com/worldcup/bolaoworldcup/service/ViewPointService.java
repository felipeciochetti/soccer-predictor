package com.worldcup.bolaoworldcup.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldcup.bolaoworldcup.model.BetUserGame;
import com.worldcup.bolaoworldcup.model.BetUserGamePoints;
import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.repository.BetUserGamePointsRepository;
import com.worldcup.bolaoworldcup.repository.BetUserGameRepository;
import com.worldcup.bolaoworldcup.repository.GameRepository;
import com.worldcup.bolaoworldcup.repository.UsuarioRepository;
import com.worldcup.bolaoworldcup.view.ViewPointView;



@Service
public class ViewPointService   implements Serializable{


	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ViewPointService.class);
	
	

	@Autowired
	BetUserGameRepository repository;
	@Autowired
	UsuarioRepository userRepository;
	@Autowired
	GameRepository gameRepository;
	@Autowired
	BetUserGamePointsRepository pointsRepository;



	public ViewPointService() {
	}



	public List<ViewPointView> getViewPoints(Long userId , Optional<Long> week) {

		List<ViewPointView> listView = new ArrayList<ViewPointView>();
		
		List<BetUserGame> lisBets =  repository.findAllByIdUserId(userId);
		List<Games> listGames  = null;
		List<BetUserGamePoints> lisPointsBets =  pointsRepository.findAllByIdUserId(userId);
		
		
		if(week.isPresent()) {
			listGames = gameRepository.findAllByRound(week.get());
		}else {
			listGames = gameRepository.findAll();
		}
		
		listGames.sort(Comparator.comparing(Games::getId));
		
		for (Games game : listGames) {
			
			System.out.println(game.getId());
			
			Long palpiteScoreHome = null;
			Long palpiteScoreVisitor  = null;
			Long points = null;
			String description = "";
			String urlHomeImagem = "";
			String urlVisitorImagem = "";
			
			Optional<BetUserGame> opBet = lisBets.stream().filter(bet -> bet.getGame().getId().equals(game.getId())).findAny();
			
			Optional<BetUserGamePoints> opPointsBet = lisPointsBets.stream().filter(po -> po.getGame().getId().equals(game.getId())).findAny();
			
			
			String home = game.getHome().getName();
			String visitor = game.getVisitor().getName();
			Long scoreHome = game.getScoreHome();
			Long scoreVisitor = game.getScoreVisitor();
			
			urlHomeImagem = game.getHome().getUrlImagem();
			urlVisitorImagem = game.getVisitor().getUrlImagem();
			
			if(opBet.isPresent()) {
				palpiteScoreHome = opBet.get().getScoreHome();
				palpiteScoreVisitor = opBet.get().getScoreVisitor();
			}
			if(opPointsBet.isPresent()) {
				points = opPointsBet.get().getPoints();
				description = opPointsBet.get().getBetType().getLabel();
			}
			
			
			
			listView.add(new ViewPointView(home, visitor, scoreHome, scoreVisitor, palpiteScoreHome, palpiteScoreVisitor, points,description,urlHomeImagem,urlVisitorImagem));
			
		}
		
		
		
		
		return listView;



	}

	

}
