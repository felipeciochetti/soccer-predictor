package com.worldcup.bolaoworldcup.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.model.Team;
import com.worldcup.bolaoworldcup.repository.GameRepository;
import com.worldcup.bolaoworldcup.repository.TeamRepository;

public class ReadCsvFileTeam {
	
	List<Team> listTeam = new ArrayList<>();
	
	TeamRepository teamRepo ;
	public ReadCsvFileTeam(TeamRepository teamRepo ) {
		this.teamRepo = teamRepo;
	}
	
	public void readFile()throws Exception, IOException { 
		int lineNumber = 0;
		ClassLoader cl = getClass().getClassLoader();
		File file = new File(cl.getResource("./games/fifa-world-cup-2022.csv").getFile());
		
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	
		    	if(lineNumber == 0) {
		    		lineNumber++;
		    		continue;
		    	}
		    	
		        String[] values = line.split(",");
		        
		        
		        if(values[1].equals("1") || values[1].equals("2") || values[1].equals("3")) {
		        
		        
		        addTeamList(values[4]);
		        addTeamList(values[5]);
		        
		        
		        }
		        
		        
		        records.add(Arrays.asList(values));
		    }
		}
		
		
		
		if (teamRepo.count() == 0) {
			listTeam.forEach(team -> teamRepo.save(team));
		}
		
		
		
		
		System.out.println("Teams > " + teamRepo.count() );
		System.out.println("done.");
		
	}
	
	
	public void addTeamList(String teamName) {
		
		
		  Optional<Team> op = listTeam.stream().filter(customer -> teamName.equals(customer.getName())).findAny();
		
		if(op.isEmpty()) {
			Team team = new Team();
			team.setName(teamName);
			listTeam.add(team);
		}
		
		
	}
	
	
	
}
