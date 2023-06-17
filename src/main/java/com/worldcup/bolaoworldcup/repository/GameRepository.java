package com.worldcup.bolaoworldcup.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.model.Team;


@CrossOrigin("http://localhost:4200")
public interface GameRepository extends JpaRepository<Games, Long> {


    Optional<Games> findById(Long id);

    Optional<Games> findByMatch(Long match);
	
    List<Games> findAllByRound(Long round);
    
}
