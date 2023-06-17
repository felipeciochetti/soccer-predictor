package com.worldcup.bolaoworldcup.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.worldcup.bolaoworldcup.model.BetUserGame;
import com.worldcup.bolaoworldcup.model.BetUserGameKey;


@CrossOrigin("http://localhost:4200")
public interface BetUserGameRepository extends JpaRepository<BetUserGame,BetUserGameKey> {


	List<BetUserGame> findAllByIdUserId(Long userId);
	
	List<BetUserGame> findAllByIdGameId(Long gameId);
	Optional<BetUserGame> findAllByIdUserIdAndGameId(Long userId,Long gameId);
}
