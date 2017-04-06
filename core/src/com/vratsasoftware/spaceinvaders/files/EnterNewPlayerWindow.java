package com.vratsasoftware.spaceinvaders.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class EnterNewPlayerWindow {

	int points; 
	public EnterNewPlayerWindow (int points) { 
		this.points = points;
	}
	public  void createNewFile(int points) {
		
		addNewPlayerScore(this.points);
	}

	public  void addNewPlayerScore(int points) {
		File input = new File("src/score.txt");
		Scanner inputFile = null;
		PrintStream outputFile = null;
		
		try {
			inputFile = new Scanner(input, "UTF-8");
			outputFile = new PrintStream(input, "UTF-8");
			outputFile.println(points);
			System.out.println(input.getAbsolutePath());
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (UnsupportedEncodingException u) {
			System.err.println(u.getMessage());
		} finally {
			if (null != inputFile) {
				inputFile.close();
			}
			if (null != outputFile) {
				outputFile.close();
			}
		}
	}
}
