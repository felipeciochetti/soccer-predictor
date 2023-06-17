package com.worldcup.bolaoworldcup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.worldcup.bolaoworldcup.api.integration.IntegrationResource;
import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.repository.GameRepository;
import com.worldcup.bolaoworldcup.service.GameService;


@AutoConfigureMockMvc
@SpringBootTest
public class GamesTest {
	
	@Mock	
	GameRepository repo;
	
	@Autowired	
	IntegrationResource controller;

	
	@Autowired	
	GameService service;
	
	@Autowired	
	MockMvc mockMvc;
	
	
	
	
	public void filterByWeek() {
		
		
		List<Games> games = repo.findAllByRound(3L);
		
		
		assertEquals(10, games.size(), "amount of games on week 3");
		
		
		
	}
	
	@Test
	public void adjustTimesTamp() {
		
		LocalDateTime localDateTime = LocalDateTime.parse("2023-04-15T16:00");
		
		ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
		
		
		ZonedDateTime instantInUTC = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
		
		System.out.println(instantInUTC);
		
		
		
	}
	
	
	@Test
	public void gamesService() throws Exception {
		
		//when(repo.findById(1L)).thenReturn(Optional.of(new Games()));
		
		
		//MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.get("/games/{id}" , 1)).andReturn();
		
		
		assertEquals(new Games(), service.getGame(3802L));
		
		
	}
	

}
