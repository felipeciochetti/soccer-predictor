package com.worldcup.bolaoworldcup.api;


import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worldcup.bolaoworldcup.exception.ResourceNotFoundException;
import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.model.League;
import com.worldcup.bolaoworldcup.model.LeagueUsuarioInvite;
import com.worldcup.bolaoworldcup.model.LeagueUsuarioInviteKey;
import com.worldcup.bolaoworldcup.model.Usuarios;
import com.worldcup.bolaoworldcup.model.enuns.StatusInvite;
import com.worldcup.bolaoworldcup.repository.LeagueClassificacaoRepository;
import com.worldcup.bolaoworldcup.repository.LeagueInvitesRepository;
import com.worldcup.bolaoworldcup.repository.LeagueRepository;
import com.worldcup.bolaoworldcup.repository.UsuarioRepository;
import com.worldcup.bolaoworldcup.service.LeagueClassificaoService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LeagueInviteResource  implements Serializable{


	@Autowired
	LeagueRepository leagueRepository;
	@Autowired
	UsuarioRepository userRepository;

	@Autowired
	LeagueClassificacaoRepository leagueClassificaoRepository;

	@Autowired
	LeagueInvitesRepository leagueRepo;

	@Autowired
	LeagueClassificaoService service;
	

	@PostMapping("/league-invites/{leagueId}")
	public ResponseEntity<LeagueUsuarioInvite> addLeagueInvite(@PathVariable("leagueId") long leagueId,@RequestParam Long userId ) {

		

		Usuarios user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user: " + userId));
		
		League league = leagueRepository.findById(leagueId).orElseThrow(() -> new ResourceNotFoundException("leagueId: " + userId));
		
		
		
		LeagueUsuarioInviteKey key = new LeagueUsuarioInviteKey();
		key.setLeagueId(leagueId);
		key.setUserId(userId);
		
		LeagueUsuarioInvite leagueUsuarioInvite = new LeagueUsuarioInvite();
		leagueUsuarioInvite.setStatus(StatusInvite.ACTIVE);
		leagueUsuarioInvite.setId(key);
		leagueUsuarioInvite.setLeague(league);
		leagueUsuarioInvite.setUser(user);
		
		
		return new  ResponseEntity<>(leagueRepo.save(leagueUsuarioInvite), HttpStatus.OK);
	}

	
	@PutMapping("/leagues-invite/{leagueId}/user/{userId}")
	public ResponseEntity<Games> updateInvite(@PathVariable("userId") long userId, @PathVariable long leagueId , @RequestParam StatusInvite status  ) throws Exception {


		LeagueUsuarioInvite leagueUsuarioInvite = leagueRepo.findAllByIdUserIdAndLeagueId(userId,leagueId).orElseThrow(() -> new ResourceNotFoundException("leagueUsuarioInvite: " + userId));
		leagueUsuarioInvite.setStatus(status);
		leagueRepo.save(leagueUsuarioInvite);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/user/{userId}/leagues-invites")
	public ResponseEntity<List<League>> getLeaguesInvitesByUser(@PathVariable("userId") long usuarioId) {

		List<LeagueUsuarioInvite> list = leagueRepo.findAllByIdUserIdAndStatus(usuarioId, StatusInvite.ACTIVE);
		
		
		
		List<League> listLeague = list.stream().map(LeagueUsuarioInvite::getLeague).collect(Collectors.toList());

		return new  ResponseEntity<>(listLeague, HttpStatus.OK);
	}
	
	
	@GetMapping("/user/{userId}/count-leagues-invites")
	public ResponseEntity<Long> getLeaguesInvitesByUserCount(@PathVariable("userId") long usuarioId) {

		Long count =  leagueRepo.countByIdUserIdAndStatus(usuarioId, StatusInvite.ACTIVE);


		return new  ResponseEntity<>(count, HttpStatus.OK);
	}
	







}