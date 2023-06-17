package com.worldcup.bolaoworldcup.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.worldcup.bolaoworldcup.model.LeagueClassificacao;
import com.worldcup.bolaoworldcup.model.LeagueClassificaoKey;
import com.worldcup.bolaoworldcup.model.enuns.StatusInvite;


@CrossOrigin("http://localhost:4200")
public interface LeagueClassificacaoRepository extends JpaRepository<LeagueClassificacao,LeagueClassificaoKey> {


	List<LeagueClassificacao> findAllByIdUserId(Long userId);
	List<LeagueClassificacao> findAllByIdLeagueId(Long leagueId);
	Optional<LeagueClassificacao> findByIdLeagueIdAndIdUserId(Long userId, Long leagueId);
	
	
	Long countByIdLeagueId(Long leagueId);
	
	@Query("SELECT max(i.place) " +
	        "FROM LeagueClassificacao as i " +
	        "WHERE i.league.id = :leagueId" +
	        "   ")
	Long findMaxSequence(@Param("leagueId") Long leagueId);
}
