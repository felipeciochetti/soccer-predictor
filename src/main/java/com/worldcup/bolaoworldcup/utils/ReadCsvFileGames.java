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
import java.util.TimeZone;
import java.util.stream.Collectors;

import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.model.Team;
import com.worldcup.bolaoworldcup.repository.GameRepository;
import com.worldcup.bolaoworldcup.repository.TeamRepository;

public class ReadCsvFileGames {
	
	List<Team> listTeam = new ArrayList<>();
	List<Games> listGames = new ArrayList<>();
	
	GameRepository gameRepo ;
	TeamRepository teamRepo ;
	public ReadCsvFileGames(GameRepository gameRepo ,TeamRepository teamRepo ) {
		this.gameRepo = gameRepo;
		this.teamRepo = teamRepo;
	}
	
	public static void main(String[] args) {
		
		
	}
	
	public void readFile()throws Exception, IOException { 
		int lineNumber = 0;
		ClassLoader cl = getClass().getClassLoader();
		File file = new File(cl.getResource("./games/fifa-world-cup-2022.csv").getFile());
		
		listTeam = teamRepo.findAll();
		
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
		        
		        addGame(values);
		        
		        }
		        
		        
		        records.add(Arrays.asList(values));
		    }
		}
		
		
		if (gameRepo.count() == 0) {
			listGames.forEach(game -> gameRepo.save(game));
		}
		
		
		
		
		
		System.out.println("Teams > " + teamRepo.count() );
		System.out.println("Games > " + gameRepo.count() );
		System.out.println("done.");
		
		//records.forEach(el -> el.forEach(x -> System.out.println(x)));
	}
	
	
	public Team findTeam(String teamName) {
		  return listTeam.stream().filter(customer -> teamName.equals(customer.getName())).findAny().orElseThrow();
	}
	
	public void addGame(String[] values) throws Exception {
		
		Games game = new Games();
		
		Team home = findTeam(values[4]);
		Team visitor = findTeam(values[5]);
		Date date = getDateFormat(values[2]);
		Long match = Long.parseLong(values[0]);
		Long round = Long.parseLong(values[1]);
		String stadium = values[3];
		String grupo = values[6];
		
		
		game.setDate(date);
		game.setGrupo(grupo);
		game.setHome(home);
		game.setVisitor(visitor);
		game.setMatch(match);
		game.setRound(round);
		game.setStadium(stadium);
		
		
		listGames.add(game);
		
	}
	
	
	public Date getDateFormat(String date) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return formatter.parse(date);
	}
}
