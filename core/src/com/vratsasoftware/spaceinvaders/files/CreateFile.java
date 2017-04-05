package com.vratsasoftware.spaceinvaders.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class CreateFile {
	public void createTheFile(){
		if(!checkIfFileExist()){

			PrintStream fileWriter=null;
			try {
			fileWriter = new PrintStream("leaderboard.txt");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		
		}
	}
	public boolean checkIfFileExist(){


		File file = new File("leaderboard.txt");
		if(file.exists()){
			return true;
		}else{
			return false;
		}
	
	}
}
