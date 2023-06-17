package com.worldcup.bolaoworldcup.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldcup.bolaoworldcup.exception.ResourceNotFoundException;
import com.worldcup.bolaoworldcup.model.BetUserGame;
import com.worldcup.bolaoworldcup.model.BetUserGameKey;
import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.model.Usuarios;
import com.worldcup.bolaoworldcup.model.enuns.Bets;
import com.worldcup.bolaoworldcup.repository.BetUserGameRepository;
import com.worldcup.bolaoworldcup.repository.GameRepository;
import com.worldcup.bolaoworldcup.repository.UsuarioRepository;
import com.worldcup.bolaoworldcup.view.BetUserGameView;

import lombok.extern.log4j.Log4j2;



@Service
@Log4j2
public class CalculatorPointsService   implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CalculatorPointsService.class);

	enum  UserBetResult{
		HOME("HOME"),
		VISITOR("VISITOR"),
		EMPATE("EMPATE");
		private String desc;
		private UserBetResult(String desc) {
			this.desc  = desc;
		}
	}






	public Bets calculateBetPoints(Games game ,BetUserGame betUserGame) throws Exception {





		UserBetResult gameWin = getGameWin(game.getScoreHome(),game.getScoreVisitor());



		Bets bet = null;
		UserBetResult userBetWin = getGameWin(betUserGame.getScoreHome(),betUserGame.getScoreVisitor());	


		if(gameWin.equals(userBetWin) && gameWin.equals(UserBetResult.EMPATE)) {
			bet = calculateBetEmpate(betUserGame, game);
		}else if (userBetWin.equals(UserBetResult.EMPATE) && !gameWin.equals(UserBetResult.EMPATE))	{
			bet = Bets.EMPATE_GARANTIDO;
		}else if(gameWin.equals(userBetWin)) {
			bet = calculateBetWin(gameWin, gameWin, betUserGame, game);
		}else {
			bet = calculateBetDerrotado(gameWin, gameWin, betUserGame, game);
		}


		log.info("Game: {} x {} ; User Bet: {} x {}  = Bet: {}" ,game.getScoreHome(), game.getScoreVisitor(),  betUserGame.getScoreHome(),betUserGame.getScoreVisitor(), bet.getLabel());

		return bet;


	}

	private Bets calculateBetEmpate(BetUserGame betUserGame , Games game ) {
		if(betUserGame.getScoreHome().equals(game.getScoreHome())  && betUserGame.getScoreVisitor().equals(game.getScoreVisitor())){
			return Bets.PLACAR_CHEIO;
		}else {
			return Bets.EMPATE;
		}
	}

	private UserBetResult getGameWin(Long homeScore , Long visitorScore) throws Exception {
		if( homeScore > visitorScore) {
			return UserBetResult.HOME;
		}else if (visitorScore > homeScore) {
			return UserBetResult.VISITOR;
		}else if (visitorScore == homeScore) {
			return UserBetResult.EMPATE;
		}

		throw new Exception("Error calculate game win");
	}

	private Bets calculateBetDerrotado(UserBetResult gameWin , UserBetResult betUserVencedor,BetUserGame betUserGame  , Games game) throws Exception {

		if(gameWin.equals(UserBetResult.HOME)  && betUserGame.getScoreVisitor().equals(game.getScoreVisitor())){
			return Bets.GOLS_PERDEDOR;
		}else if(gameWin.equals(UserBetResult.VISITOR)  && betUserGame.getScoreHome().equals(game.getScoreHome())){
			return Bets.GOLS_PERDEDOR;
		}else if(gameWin.equals(UserBetResult.EMPATE)  && (betUserGame.getScoreVisitor().equals(game.getScoreVisitor()) || betUserGame.getScoreHome().equals(game.getScoreHome()))){
			return Bets.GOLS_ANY_TEAM;
		}

		return Bets.EMPTY;

	}


	private Bets calculateBetWin(UserBetResult gameWin , UserBetResult betUserVencedor,BetUserGame betUserGame  , Games game) throws Exception {




		if(betUserGame.getScoreHome().equals(game.getScoreHome())  && betUserGame.getScoreVisitor().equals(game.getScoreVisitor())){
			return Bets.PLACAR_CHEIO;
		}else if(UserBetResult.HOME.equals(betUserVencedor) &&  (betUserGame.getScoreHome() == game.getScoreHome() || betUserGame.getScoreVisitor() == game.getScoreVisitor())) {
			return Bets.TIME_VENCEDOR_GOLS;
		}else if(UserBetResult.VISITOR.equals(betUserVencedor) &&  (betUserGame.getScoreHome() == game.getScoreHome() || betUserGame.getScoreVisitor() == game.getScoreVisitor())) {
			return Bets.TIME_VENCEDOR_GOLS;
		}else {
			return Bets.TIME_VENCEDOR_SEM_GOLS;
		}

	}








}
