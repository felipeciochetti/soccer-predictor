package com.worldcup.bolaoworldcup.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.worldcup.bolaoworldcup.exception.ResourceNotFoundException;
import com.worldcup.bolaoworldcup.model.BetUserGame;
import com.worldcup.bolaoworldcup.model.BetUserGameKey;
import com.worldcup.bolaoworldcup.model.BetUserGamePoints;
import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.model.Usuarios;
import com.worldcup.bolaoworldcup.model.enuns.Bets;
import com.worldcup.bolaoworldcup.repository.BetUserGamePointsRepository;
import com.worldcup.bolaoworldcup.repository.BetUserGameRepository;
import com.worldcup.bolaoworldcup.repository.GameRepository;
import com.worldcup.bolaoworldcup.repository.UsuarioRepository;
import com.worldcup.bolaoworldcup.view.BetUserGameView;
import com.worldcup.bolaoworldcup.view.UserPointsView;



@Service
public class BetUserPointsGameService   implements Serializable{


	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BetUserPointsGameService.class);



	@Autowired
	BetUserGameRepository repository;
	@Autowired
	BetUserGamePointsRepository userBetPointsRepository;
	@Autowired
	UsuarioRepository userRepository;
	@Autowired
	GameRepository gameRepository;
	@Autowired
	BetUserGamePointsRepository pointsService;


	public void betUserBetGameService(Games game) throws Exception {


		List<BetUserGame> listUsersBets = repository.findAllByIdGameId(game.getId());


		for (BetUserGame betUserGame : listUsersBets) {

			CalculatorPointsService calculator = new CalculatorPointsService();

			Bets bet = calculator.calculateBetPoints(game, betUserGame);

			adicionar(game, betUserGame.getUser(), bet);


		}


	}

	public BetUserGamePoints adicionar(Games game , Usuarios user,Bets bet) {


		BetUserGamePoints betObj  = new BetUserGamePoints();

		//Usuarios user = userRepository.findById().orElseThrow(() -> new ResourceNotFoundException("User: " + userId));
		//	Games game = gameRepository.findById(game.getGameId()).orElseThrow(() -> new ResourceNotFoundException("game: " + bet.getGameId()));


		BetUserGameKey fk = new BetUserGameKey();
		fk.setGameId(game.getId());
		fk.setUserId(user.getId());
		betObj.setId(fk);
		betObj.setGame(game);
		betObj.setUser(user);
		betObj.setBetType(bet);
		betObj.setPoints(bet.getValor());
		betObj.setDate(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime());

		return userBetPointsRepository.save(betObj);



	}


	public List<UserPointsView> getAllBet() {

		List<Object[]> points =  pointsService.sumPointAllUser();

		List<UserPointsView> returnList = new ArrayList<UserPointsView>();
		for (Object[] obj : points) {
			returnList.add(new UserPointsView(Long.parseLong(((BigInteger) obj[1]).toString()) ,(String) obj[2],Long.parseLong(((java.math.BigDecimal) obj[0]).toString())));
		}
		
		return returnList;
	}



}
