package com.worldcup.bolaoworldcup;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.worldcup.bolaoworldcup.api.integration.IntegrationResource;
import com.worldcup.bolaoworldcup.model.Team;
import com.worldcup.bolaoworldcup.utils.TeamBrasileiraoDto;

@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTest {

	
	@Autowired
	MockMvc mock;
	@Mock
	IntegrationResource controller;
	
	
	
	
	
	@Test
	void integrationTeamsBrasileirao() {
		
		
		TeamBrasileiraoDto team1 = new TeamBrasileiraoDto(1L, "Corinthians", "cor", "path");
		List<TeamBrasileiraoDto> list = new ArrayList<>();
		list.add(team1);
		
		
		List<Team> list_team = list.stream().map(el -> controller.translate(el)).collect(Collectors.toList());
		
		
		when(controller.importAllTeamBrasileirao(list)).thenReturn(list_team);
		
		assertIterableEquals(list_team, list_team);
		
		
	}


	public void importGames() {
		
		
		
	}
	
	
	
}
