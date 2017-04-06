package com.vratsasoftware.spaceinvaders.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class writeInFile {

	int points;
	String name;

	public writeInFile(int score, String name) {
		this.points = score;
		this.name = name;
	}


	// TODO Make that shit work.
	// Think of an algorithm to sort the highscores
	// TODO IT SHIT, you have 1 fucking day left!
	public static void addNewPlayerScore(int points, String name) {
		File input = new File("C:\\Users\\velis\\Documents\\SpaceInvaders\\core\\src\\score.txt");
		Scanner inputFile = null;
		FileWriter outputFile = null;

		try {
			inputFile = new Scanner(input, "UTF-8");
			outputFile = new FileWriter(input, true);
			outputFile.append("\n" + name + " : " + points);
			System.out.println(input.getAbsolutePath());
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (UnsupportedEncodingException u) {
			System.err.println(u.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != inputFile) {
				inputFile.close();
			}
			if (null != outputFile) {
				try {
					outputFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
