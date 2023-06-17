package com.worldcup.bolaoworldcup.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.model.Team;
import com.worldcup.bolaoworldcup.repository.GameRepository;
import com.worldcup.bolaoworldcup.repository.TeamRepository;
import com.worldcup.bolaoworldcup.serializer.WriterReader;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ExtractDBData {
	GameRepository gameRepo ;
	TeamRepository teamRepo ;

	List<Team> listTeam = new ArrayList<Team>();

	List<Team> listEntityTeam = new ArrayList<Team>();

	public ExtractDBData(TeamRepository teamRepo,GameRepository gameRepo ) {
		this.teamRepo = teamRepo;
		this.gameRepo = gameRepo;
	}


	public void extractDB() {

		List<Team> listTeam = teamRepo.findAll();


		WriterReader writer = new WriterReader();



		for (Team team : listTeam) {
			//writer.writerObj(team);
		}


	}
	public void extractDBGame() {

		List<Games> games = gameRepo.findAll();


		WriterReader writer = new WriterReader();



		for (Games team : games) {
			writer.writerObj(team);
		}


	}

	public void readTeam() {
		listTeam = new ArrayList<Team>();

		ClassLoader cl = getClass().getClassLoader();
		File folder = new File(cl.getResource("./team/").getPath());

		System.out.println(folder.list().length);


		for (File f : folder.listFiles()) {

			try {
				
			WriterReader obj = new WriterReader();
			Team team =  (Team) obj.readerObj(f);

			listTeam.add(team);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		System.out.println(listTeam.size());
		for (Team team : listTeam) {
			try {
				System.out.println(team);
				listEntityTeam.add(teamRepo.save(team));
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		

	}

	public void readGame() {

		for (int i = 1; i < 49; i++) {
			ClassLoader cl = getClass().getClassLoader();
			File file = new File(cl.getResource("./games/" + i + ".txt").getFile());


			WriterReader obj = new WriterReader();
			Games g =  (Games) obj.readerObj(file);



			g.setHome(listEntityTeam.stream().filter(home -> home.getName().equals(g.getHome().getName())).findAny().get());
			g.setVisitor(listEntityTeam.stream().filter(visitor -> visitor.getName().equals(g.getVisitor().getName())).findAny().get());

			gameRepo.save(g);

		}

	}


	public void saveEntityes() {

		if(teamRepo.findAll().isEmpty()) {
			//readTeam();
			//readGame();
		}
		System.out.println("Teams > " + teamRepo.findAll().size());
		System.out.println("Games > " + gameRepo.findAll().size());
	}
}
