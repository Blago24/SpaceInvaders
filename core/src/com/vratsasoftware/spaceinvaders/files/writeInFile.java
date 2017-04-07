package com.vratsasoftware.spaceinvaders.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class writeInFile {

	int points;

	public writeInFile(int score) {
		this.points = score;
	}

	public void addNewPlayerScore(int points) {
		File input = new File("C:\\Users\\velis\\Documents\\SpaceInvaders\\core\\src\\score.txt");
		Scanner inputFile = null;
		FileWriter outputFile = null;

		try {
			inputFile = new Scanner(input, "UTF-8");
			outputFile = new FileWriter(input, true);
			outputFile.append("\n" + points);
			HighscoreFileManager hs = new HighscoreFileManager();
			hs.sortHighscores();
			System.out.println(input.getAbsolutePath());
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (UnsupportedEncodingException u) {
			System.err.println(u.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != inputFile) {
				inputFile.close();
			}
			if (null != outputFile) {
				try {
					outputFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
