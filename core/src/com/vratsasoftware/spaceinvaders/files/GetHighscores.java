package com.vratsasoftware.spaceinvaders.files;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class GetHighscores {

	String firstScore;
	String secondScore;
	String thirdScore;

	
	public GetHighscores() throws FileNotFoundException, IOException { 
		this.firstScore = getFirstScore(getAmountOfLines());
		this.secondScore = getSecondHighScore(getAmountOfLines());
		this.thirdScore = getThirdHighScore(getAmountOfLines());
	}

	public String getThirdHighScore(int numberOfLines) throws FileNotFoundException {
		boolean processCompleted = false;
		int lineNeeded = numberOfLines - 2;
		int currentLine = 0;
		String line = null;
		File text = new File("C:\\Users\\velis\\Documents\\SpaceInvaders\\core\\src\\SortedHighScores.txt");
		Scanner scanner = new Scanner(new BufferedReader(new FileReader(text)));

		while (processCompleted == false) {
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				currentLine++;
				if (currentLine == lineNeeded) {
					thirdScore = line; 
					processCompleted = true;
					break;

				}
			}
		}

		if (scanner != null) {
			scanner.close();
		}
		
		if (processCompleted) {
			return line;
		}

		return line;
	}

	public String getSecondHighScore(int numberOfLines) throws FileNotFoundException {
		boolean processCompleted = false;
		int lineNeeded = numberOfLines - 1;
		int currentLine = 0;
		String line = null;
		File text = new File("C:\\Users\\velis\\Documents\\SpaceInvaders\\core\\src\\SortedHighScores.txt");
		Scanner scanner = new Scanner(new BufferedReader(new FileReader(text)));

		while (processCompleted == false) {
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				currentLine++;
				if (currentLine == lineNeeded) {
					System.out.println(line);
					processCompleted = true;
					break;
				}
			}
		}

		if (scanner != null) {
			scanner.close();
		}
		if (processCompleted) {
			return line;
		}
		return line;
	}

	public String getFirstScore(int numberOfLines) throws FileNotFoundException {

		boolean processCompleted = false;
		int lineNeeded = numberOfLines;
		int currentLine = 0;
		String line = null;
		File text = new File("C:\\Users\\velis\\Documents\\SpaceInvaders\\core\\src\\SortedHighScores.txt");
		Scanner scanner = new Scanner(new BufferedReader(new FileReader(text)));

		while (processCompleted == false) {
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				currentLine++;
				if (currentLine == lineNeeded) {
					System.out.println(line);
					processCompleted = true;
					break;
				}
			}
		}

		if (scanner != null) {
			scanner.close();
		}
		if (processCompleted) {
			return line;
		}
		return line;
	}

	public int getAmountOfLines() throws IOException {
		File text = new File("C:\\Users\\velis\\Documents\\SpaceInvaders\\core\\src\\SortedHighScores.txt");
		InputStream is = new BufferedInputStream(new FileInputStream(text));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count + 1;
		} finally {
			is.close();
		}
	}
	
	public int getAmountOfLinesInTheScoresFile() throws IOException {
		File text = new File("C:\\Users\\velis\\Documents\\SpaceInvaders\\core\\src\\score.txt");
		InputStream is = new BufferedInputStream(new FileInputStream(text));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count + 1;
		} finally {
			is.close();
		}
	}

	public String getThirdScore() {
		return thirdScore;
	}

	public String getFirstScore() {
		return firstScore;
	}

	public String getSecondScore() {
		return secondScore;
	}
}
