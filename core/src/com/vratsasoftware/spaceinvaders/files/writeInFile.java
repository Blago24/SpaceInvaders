package com.vratsasoftware.spaceinvaders.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class writeInFile {

	public void createNewFile(){

		File file = new File("leaderboard.txt");
		if(!file.exists()){
			PrintStream fileWriter=null;
			try {
				fileWriter = new PrintStream("leaderboard.txt");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		}else{
			System.out.println("WE HAVE");
		}
	}
	public void addNewPlayerScore(String name, int points){
		
	}
}
