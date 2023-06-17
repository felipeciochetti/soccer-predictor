package com.worldcup.bolaoworldcup;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.worldcup.bolaoworldcup.repository.GameRepository;
import com.worldcup.bolaoworldcup.repository.TeamRepository;
import com.worldcup.bolaoworldcup.utils.ExtractDBData;
import com.worldcup.bolaoworldcup.utils.ReadCsvFileGames;
import com.worldcup.bolaoworldcup.utils.ReadCsvFileTeam;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
    private TeamRepository userRepository;
	@Autowired
    private GameRepository gameRepository;

    

    public void run(ApplicationArguments args) {
     try {
    	 new ExtractDBData(userRepository,gameRepository).saveEntityes();
    	// new ReadCsvFileTeam(userRepository).readFile();
    	// new ReadCsvFileGames(gameRepository, userRepository).readFile();
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}