package com.worldcup.bolaoworldcup.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.worldcup.bolaoworldcup.model.Games;

public class WriterReader {

	public WriterReader() {
		// TODO Auto-generated constructor stub
	}

	public Object  readerObj(File file) {
		FileInputStream f = null;
		ObjectInputStream o = null;
		try {
			f = new FileInputStream(file);
			o = new ObjectInputStream(f);

			return  o.readObject();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {

			try {
				o.close();
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}



		return null;	

	}

	public void writerObj(Games team) {
		try {
			FileOutputStream f = new FileOutputStream(new File(team.getRound() +".txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);


			o.writeObject(team);




			o.close();
			f.close();

			System.out.println("File: " + team.getMatch());

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}

}
