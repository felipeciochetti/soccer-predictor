package com.worldcup.bolaoworldcup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.worldcup.bolaoworldcup.model.BetUserGame;
import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.model.enuns.Bets;
import com.worldcup.bolaoworldcup.service.CalculatorPointsService;

@SpringBootTest
public class CalculateBetPointTests {



	
	@Test
	public void calculatePointToPlacarCheioWithWinHome() throws Exception {
		CalculatorPointsService calculator = new CalculatorPointsService();
		Games game  = new Games();
		game.setScoreHome(3L);
		game.setScoreVisitor(2L);
		BetUserGame betUserGame = new BetUserGame();
		betUserGame.setScoreHome(3L);
		betUserGame.setScoreVisitor(2L);
		assertEquals(Bets.PLACAR_CHEIO, calculator.calculateBetPoints(game, betUserGame)); 
		;
		
		
	}
	@Test
	public void calculatePointToPlacarCheioWithWinVisitor() throws Exception {
		CalculatorPointsService calculator = new CalculatorPointsService();
		Games game  = new Games();
		game.setScoreHome(3L);
		game.setScoreVisitor(4L);
		BetUserGame betUserGame = new BetUserGame();
		betUserGame.setScoreHome(3L);
		betUserGame.setScoreVisitor(4L);
		assertEquals(Bets.PLACAR_CHEIO, calculator.calculateBetPoints(game, betUserGame)); 
		;
		
		
	}
	@Test
	public void calculatePointToWinWithoutGolsHome() throws Exception {
		CalculatorPointsService calculator = new CalculatorPointsService();
		Games game  = new Games();
		game.setScoreHome(3L);
		game.setScoreVisitor(2L);
		BetUserGame betUserGame = new BetUserGame();
		betUserGame.setScoreHome(4L);
		betUserGame.setScoreVisitor(3L);
		assertEquals(Bets.TIME_VENCEDOR_SEM_GOLS, calculator.calculateBetPoints(game, betUserGame)); 
		;
		
		
	}
	@Test
	public void calculatePointToWinWithoutGolsVisitor() throws Exception {
		CalculatorPointsService calculator = new CalculatorPointsService();
		Games game  = new Games();
		game.setScoreHome(3L);
		game.setScoreVisitor(4L);
		BetUserGame betUserGame = new BetUserGame();
		betUserGame.setScoreHome(4L);
		betUserGame.setScoreVisitor(5L);
		assertEquals(Bets.TIME_VENCEDOR_SEM_GOLS, calculator.calculateBetPoints(game, betUserGame)); 
		;
		
		
	}
	@Test
	public void calculatePointToWinGols() throws Exception {
		CalculatorPointsService calculator = new CalculatorPointsService();
		Games game  = new Games();
		game.setScoreHome(3L);
		game.setScoreVisitor(4L);
		BetUserGame betUserGame = new BetUserGame();
		betUserGame.setScoreHome(3L);
		betUserGame.setScoreVisitor(5L);
		assertEquals(Bets.TIME_VENCEDOR_GOLS, calculator.calculateBetPoints(game, betUserGame)); 
		;
	}
	
	@Test
	public void calculatePointToWinWithGolsHome() throws Exception {
		CalculatorPointsService calculator = new CalculatorPointsService();
		Games game  = new Games();
		game.setScoreHome(3L);
		game.setScoreVisitor(1L);
		BetUserGame betUserGame = new BetUserGame();
		betUserGame.setScoreHome(3L);
		betUserGame.setScoreVisitor(2L);
		assertEquals(Bets.TIME_VENCEDOR_GOLS, calculator.calculateBetPoints(game, betUserGame)); 
		;
		
		
	}
	@Test
	public void calculatePointToWinWithGolsVisitor() throws Exception {
		CalculatorPointsService calculator = new CalculatorPointsService();
		Games game  = new Games();
		game.setScoreHome(3L);
		game.setScoreVisitor(4L);
		BetUserGame betUserGame = new BetUserGame();
		betUserGame.setScoreHome(2L);
		betUserGame.setScoreVisitor(4L);
		assertEquals(Bets.TIME_VENCEDOR_GOLS, calculator.calculateBetPoints(game, betUserGame)); 
		;
		
		
	}
	
	@Test
	public void calculatePointToEmpate() throws Exception {
		CalculatorPointsService calculator = new CalculatorPointsService();
		Games game  = new Games();
		game.setScoreHome(3L);
		game.setScoreVisitor(3L);
		BetUserGame betUserGame = new BetUserGame();
		betUserGame.setScoreHome(3L);
		betUserGame.setScoreVisitor(3L);
		assertEquals(Bets.PLACAR_CHEIO, calculator.calculateBetPoints(game, betUserGame)); 
		;
		
		
	}
	@Test
	public void calculatePointToEmpateWithoutGols() throws Exception {
		CalculatorPointsService calculator = new CalculatorPointsService();
		Games game  = new Games();
		game.setScoreHome(3L);
		game.setScoreVisitor(3L);
		BetUserGame betUserGame = new BetUserGame();
		betUserGame.setScoreHome(1L);
		betUserGame.setScoreVisitor(1L);
		assertEquals(Bets.EMPATE, calculator.calculateBetPoints(game, betUserGame)); 
		;
		
		
	}
	@Test
	public void calculatePointToWrongBetEmpty() throws Exception {
		CalculatorPointsService calculator = new CalculatorPointsService();
		Games game  = new Games();
		game.setScoreHome(3L);
		game.setScoreVisitor(3L);
		BetUserGame betUserGame = new BetUserGame();
		betUserGame.setScoreHome(5L);
		betUserGame.setScoreVisitor(1L);
		assertEquals(Bets.EMPTY, calculator.calculateBetPoints(game, betUserGame)); 
		;
		
		
	}
	@Test
	public void calculatePointToWrongBetEmptWithNumberOfGols() throws Exception {
		CalculatorPointsService calculator = new CalculatorPointsService();
		Games game  = new Games();
		game.setScoreHome(3L);
		game.setScoreVisitor(3L);
		BetUserGame betUserGame = new BetUserGame();
		betUserGame.setScoreHome(3L);
		betUserGame.setScoreVisitor(1L);
		assertEquals(Bets.GOLS_ANY_TEAM, calculator.calculateBetPoints(game, betUserGame)); 
		;
		
		
	}

}