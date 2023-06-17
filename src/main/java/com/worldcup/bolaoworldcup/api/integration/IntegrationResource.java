package com.worldcup.bolaoworldcup.api.integration;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.model.Team;
import com.worldcup.bolaoworldcup.repository.GameRepository;
import com.worldcup.bolaoworldcup.repository.TeamRepository;
import com.worldcup.bolaoworldcup.service.BetUserPointsGameService;
import com.worldcup.bolaoworldcup.service.LeagueClassificaoService;
import com.worldcup.bolaoworldcup.utils.GameBrasileiraoDto;
import com.worldcup.bolaoworldcup.utils.Root;
import com.worldcup.bolaoworldcup.utils.TeamBrasileiraoDto;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class IntegrationResource  implements Serializable{



	@Autowired
	GameRepository repository;


	@Autowired
	BetUserPointsGameService pointsService;
	@Autowired
	LeagueClassificaoService leagueClassificaoService;
	@Autowired
	TeamRepository repo;
	@Autowired
	TeamRepository teamRepo ;


	private List<Team> listTeam;


	@PostMapping("/integration/teams")
	public List<Team> importAllTeamBrasileirao(@RequestBody List<TeamBrasileiraoDto> teams) {

		
		List<Team> list_team = teams.stream().map(el -> translate(el)).collect(Collectors.toList());
		
		list_team.forEach(team -> repo.save(team));
			
		return list_team;
	}


	@PostMapping("/integration/games")
	public List<Games> importAllGamesBrasileirao(@RequestBody List<com.worldcup.bolaoworldcup.utils.Root> listRoot) {

		listTeam = teamRepo.findAll();
		List<Games> list_games = new ArrayList<Games>();
		
		
		for (Root root : listRoot) {
			
			List<Games> list_team = root.getGames().stream().map(el -> translate(root.getMatch_week() ,el)).collect(Collectors.toList());
			list_team.forEach(game -> {
				repository.save(game);
				list_games.add(game);
			});
		
		
		
		}
			
		return list_games;
	}




	public Team translate(TeamBrasileiraoDto dto) {

		Team team = new Team();
		team.setName(dto.getNome_popular());
		team.setDescription(dto.getSigla());
		team.setImage(dto.getEscudo());
		team.setUrlImagem(dto.getEscudo());
		
		
		return team;
	}

	public Games translate(int matchWeek, GameBrasileiraoDto dto) {
		
		Team home = listTeam.stream().filter(el -> dto.getEquipes().getMandante().getSigla().equals(el.getDescription())).findAny().orElseThrow();
		
		Team away = listTeam.stream().filter(el -> dto.getEquipes().getVisitante().getSigla().equals(el.getDescription())).findAny().orElseThrow();
		
		Games games = new Games();
			
		ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(dto.getData_realizacao(), zoneId);
		ZonedDateTime instantInUTC = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
		games.setDate(Date.from(instantInUTC.toInstant()));
		games.setHome(home);
		games.setVisitor(away);
		Optional.ofNullable(dto.getSede()).ifPresent(elookl -> games.setStadium(elookl.getNome_popular()));
		Optional.ofNullable(dto.getPlacar_oficial_mandante()).ifPresent(el -> games.setScoreHome(el.longValue()));
		Optional.ofNullable(dto.getPlacar_oficial_visitante()).ifPresent(el -> games.setScoreVisitor(el.longValue()));
		games.setRound((long) matchWeek);
		
		
		
	
		
		;
		return games;
	}




}