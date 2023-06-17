package com.worldcup.bolaoworldcup.service;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldcup.bolaoworldcup.exception.ResourceNotFoundException;
import com.worldcup.bolaoworldcup.model.League;
import com.worldcup.bolaoworldcup.model.LeagueClassificacao;
import com.worldcup.bolaoworldcup.model.LeagueClassificaoKey;
import com.worldcup.bolaoworldcup.model.LeagueUsuarioInvite;
import com.worldcup.bolaoworldcup.model.Usuarios;
import com.worldcup.bolaoworldcup.model.enuns.StatusInvite;
import com.worldcup.bolaoworldcup.repository.BetUserGamePointsRepository;
import com.worldcup.bolaoworldcup.repository.BetUserGameRepository;
import com.worldcup.bolaoworldcup.repository.LeagueClassificacaoRepository;
import com.worldcup.bolaoworldcup.repository.LeagueInvitesRepository;
import com.worldcup.bolaoworldcup.repository.LeagueRepository;
import com.worldcup.bolaoworldcup.repository.UsuarioRepository;
import com.worldcup.bolaoworldcup.view.UserPointsView;



@Service
public class LeagueClassificaoService   implements Serializable{


	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LeagueClassificaoService.class);



	@Autowired
	BetUserGameRepository repository;
	@Autowired
	BetUserGamePointsRepository userBetPointsRepository;
	@Autowired
	UsuarioRepository userRepository;
	@Autowired
	LeagueClassificacaoRepository leagueClassificacaoRepository;
	@Autowired
	LeagueRepository leagueRepository;


	@Autowired
	LeagueInvitesRepository leagueInvitesRepository;


	@Autowired
	BetUserPointsGameService pointsService;

	public void leagueClassificaoService() throws Exception {



		List<UserPointsView> userBets = pointsService.getAllBet();
		for (UserPointsView user : userBets) {
			List<LeagueClassificacao> leaguesPoints = leagueClassificacaoRepository.findAllByIdUserId(user.getId());

			for (LeagueClassificacao ranking : leaguesPoints) {
				ranking.setPoints(user.getPoints());
				leagueClassificacaoRepository.save(ranking);
			}




		}

		List<League> leagues = leagueRepository.findAll();

		for (League league : leagues) {


			List<LeagueClassificacao> leaguesRanking =  leagueClassificacaoRepository.findAllByIdLeagueId(league.getId());


			List<LeagueClassificacao> sortedLeaguesRanking = leaguesRanking.stream()
					.sorted(Comparator.comparingLong(LeagueClassificacao::getPoints).reversed())
					.collect(Collectors.toList());


			long x = 1L;
			for (LeagueClassificacao ranking : sortedLeaguesRanking) {
				ranking.setPlace(x++);
				leagueClassificacaoRepository.save(ranking);
			}

		}

	}

	public LeagueClassificacao adicionar(Long usuarioId, Long  leagueId) { 
		return adicionar(usuarioId, leagueId, true);
	}

	public LeagueClassificacao adicionar(Long usuarioId, Long  leagueId, boolean isCheckInvite) {


		if(isCheckInvite) {
			LeagueUsuarioInvite leagueUsuarioInvite = leagueInvitesRepository.findAllByIdUserIdAndLeagueId(usuarioId,leagueId).orElseThrow(() -> new ResourceNotFoundException("leagueUsuarioInvite: " + usuarioId));
			leagueUsuarioInvite.setStatus(StatusInvite.ACCEPTED);
			leagueInvitesRepository.save(leagueUsuarioInvite);
		}

		Optional<LeagueClassificacao> opObj = leagueClassificacaoRepository.findByIdLeagueIdAndIdUserId(usuarioId, leagueId);
		if(opObj.isPresent()) {
			return  opObj.get();
		}


		Usuarios user = userRepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("User: " + usuarioId));
		League league = leagueRepository.findById(leagueId).orElseThrow(() -> new ResourceNotFoundException("leagueId: " + leagueId));


		LeagueClassificaoKey key = new LeagueClassificaoKey();
		key.setLeagueId(leagueId);
		key.setUserId(usuarioId);

		LeagueClassificacao leagueClassificacao = new LeagueClassificacao();
		leagueClassificacao.setId(key);
		leagueClassificacao.setLeague(league);
		leagueClassificacao.setUser(user);
		leagueClassificacao.setPoints(0L);
		leagueClassificacao.setPlace(leagueClassificacaoRepository.findMaxSequence(leagueId));

		return leagueClassificacaoRepository.save(leagueClassificacao);


	}	



}
