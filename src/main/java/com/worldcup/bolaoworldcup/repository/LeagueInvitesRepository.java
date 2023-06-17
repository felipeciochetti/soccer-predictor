package com.worldcup.bolaoworldcup.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.worldcup.bolaoworldcup.model.LeagueUsuarioInvite;
import com.worldcup.bolaoworldcup.model.League;
import com.worldcup.bolaoworldcup.model.LeagueUsuarioInvite;
import com.worldcup.bolaoworldcup.model.LeagueUsuarioInviteKey;
import com.worldcup.bolaoworldcup.model.enuns.StatusInvite;


@CrossOrigin("http://localhost:4200")
public interface LeagueInvitesRepository extends JpaRepository<LeagueUsuarioInvite,LeagueUsuarioInviteKey> {


	List<LeagueUsuarioInvite> findAllByIdUserId(Long userId);

	List<LeagueUsuarioInvite> findAllByIdUserIdAndStatus(Long userId, StatusInvite status);

	Long countByIdUserIdAndStatus(Long userId, StatusInvite status);


	List<LeagueUsuarioInvite> findAllByIdLeagueId(Long gameId);
	Optional<LeagueUsuarioInvite> findAllByIdUserIdAndLeagueId(Long userId,Long gameId);


}
