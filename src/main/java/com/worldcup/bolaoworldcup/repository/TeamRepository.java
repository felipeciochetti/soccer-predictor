package com.worldcup.bolaoworldcup.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.worldcup.bolaoworldcup.model.Team;


@CrossOrigin("http://localhost:4200")
public interface TeamRepository extends JpaRepository<Team, Long> {


    List<Team> findByName(String name);

	
}
