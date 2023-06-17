package com.worldcup.bolaoworldcup.api;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worldcup.bolaoworldcup.exception.ResourceNotFoundException;
import com.worldcup.bolaoworldcup.model.League;
import com.worldcup.bolaoworldcup.model.LeagueClassificacao;
import com.worldcup.bolaoworldcup.model.Usuarios;
import com.worldcup.bolaoworldcup.repository.LeagueClassificacaoRepository;
import com.worldcup.bolaoworldcup.repository.LeagueRepository;
import com.worldcup.bolaoworldcup.repository.UsuarioRepository;
import com.worldcup.bolaoworldcup.service.LeagueClassificaoService;
import com.worldcup.bolaoworldcup.view.RankingView;
import com.worldcup.bolaoworldcup.view.UserPointsPlaceView;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LeagueResource  implements Serializable{



	@Autowired
	LeagueRepository repository;

	@Autowired
	LeagueClassificacaoRepository leagueClassificaoRepository;

	@Autowired
	LeagueRepository leagueRepo;

	@Autowired
	LeagueClassificaoService service;

	@Autowired
	UsuarioRepository userRepository;

	@PostMapping("/league")
	public ResponseEntity<League> addLeague(@RequestBody League league) {

		Usuarios user = userRepository.findById(league.getAdminId()).orElseThrow(() -> new ResourceNotFoundException("user: "));
		league.setAdmin(user);
		
		 league = leagueRepo.save(league);
		
		 service.adicionar(league.getAdminId(), league.getId() , false);
		


		return new  ResponseEntity<>(league, HttpStatus.OK);
	}

	@PostMapping("/user/{usuarioId}/league/{leagueId}")
	public ResponseEntity<LeagueClassificacao> addUsuarioLeague(@PathVariable("usuarioId") long usuarioId,@PathVariable("leagueId") long leagueId) {


		LeagueClassificacao obj = null;

		obj =  service.adicionar(usuarioId, leagueId);

		return new  ResponseEntity<>(obj, HttpStatus.OK);
	}


	@GetMapping("/user/{userId}/leagues")
	public ResponseEntity<List<League>> getLeaguesByUser(@PathVariable("userId") long usuarioId) {

		List<League> list = repository.findAllLeagueByUserId(usuarioId);
		
		
		for (League league : list) {
			long x = leagueClassificaoRepository.countByIdLeagueId(league.getId());
			league.setParticipantes(x);
			league.setAdminId(league.getAdmin().getId());
		}
		

		return new  ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/admin/{adminId}/leagues")
	public ResponseEntity<List<League>> getLeaguesByAdmin(@PathVariable("adminId") long usuarioId) {

		List<League> list = repository.findAllByAdmin_Id(usuarioId);


		return new  ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/league/{leagueId}")
	public ResponseEntity<League> getLeaguesById(@PathVariable("leagueId") long leagueId) {

		League league = repository.findById(leagueId).orElse(null);


		return new  ResponseEntity<>(league, HttpStatus.OK);
	}

	@GetMapping("/league")
	public ResponseEntity<List<League>> getAllLeagues(@RequestParam(required = false) String name) {

		List<League> league = new ArrayList<League>();

		if (name == null)
			repository.findAll().forEach(league::add);
		else {
			repository.findByNameLike("%" + name + "%").forEach(league::add);
		}







		return new  ResponseEntity<>(league, HttpStatus.OK);
	}


	@GetMapping("/league/{leagueId}/ranking")
	public ResponseEntity<RankingView> getLeagues(@PathVariable("leagueId") long leagueId) {

		RankingView league = new RankingView();

		List<LeagueClassificacao> leagueRanking =  leagueClassificaoRepository.findAllByIdLeagueId(leagueId);

		league.setId(leagueRanking.get(0).getLeague().getId());
		league.setLeague(leagueRanking.get(0).getLeague().getName());

		List<UserPointsPlaceView> listUser = new ArrayList<>();

		for (LeagueClassificacao leagueClassificacao : leagueRanking) {
			listUser.add(new UserPointsPlaceView(leagueClassificacao.getUser().getId(),leagueClassificacao.getPlace(),leagueClassificacao.getUser().getName(), leagueClassificacao.getPoints()));
		}


		List<UserPointsPlaceView> sortedlistUser = listUser.stream()
				.sorted(Comparator.comparingLong(UserPointsPlaceView::getPoints).reversed())
				.collect(Collectors.toList());

		league.setListUser(sortedlistUser);

		return new  ResponseEntity<>(league, HttpStatus.OK);
	}











}