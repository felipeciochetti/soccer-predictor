package com.worldcup.bolaoworldcup.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.worldcup.bolaoworldcup.model.League;


@CrossOrigin("http://localhost:4200")
public interface LeagueRepository extends JpaRepository<League, Long> {


    Optional<League> findById(Long id);
    List<League> findByName(String name);
	List<League> findByNameLike(String name);
    
    @Query(value = "select * from League where id in (select league_id from league_classificacao where user_id = ?1)", 
    		  nativeQuery = true)
	List<League> findAllLeagueByUserId(Long userId);
    
    List<League> findAllByAdmin_Id(Long userId);
}
