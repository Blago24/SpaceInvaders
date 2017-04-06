package com.vratsasoftware.spaceinvaders.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HighscoreFileManager {
	int size = 10;
	public void sortHighscores() {
		File input = new File("src/score.txt");
		File output = new File("src/SortedHighScores.txt");
		PrintStream addResults;
		Scanner inputReader = null;
		int currentLine = 0;
		int[] results = null;
		
		int i = 0;

		boolean processCompleted = false;

		try {
			while (processCompleted == false) {
				addResults = new PrintStream(output);
				inputReader = new Scanner(input, "UTF-8");
				results = new int[size];
				while (inputReader.hasNextLine()) {
					currentLine = inputReader.nextInt();
					results[i] = currentLine;
					i++;
					if (i == size - 1) {
						Arrays.sort(results);
						for (int result : results) {
							addResults.println(result);
							System.out.println(result);
							processCompleted = true;
						}
					}
				}
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (inputReader != null) {
				inputReader.close();
			}
		}

	}
	
}
