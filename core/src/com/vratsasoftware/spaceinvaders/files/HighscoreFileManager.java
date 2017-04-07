package com.vratsasoftware.spaceinvaders.files;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HighscoreFileManager {
	
	static int size;

	public HighscoreFileManager() {
		sortHighscores();
	}

	public void sortHighscores() {
		File input = new File("C:\\Users\\velis\\Documents\\SpaceInvaders\\core\\src\\score.txt");
		File output = new File("C:\\Users\\velis\\Documents\\SpaceInvaders\\core\\src\\SortedHighScores.txt");
		PrintStream addResults;
		Scanner inputReader = null;
		GetHighscores gh;

		int i = 0;
		int currentLine = 0;
		int[] results = null;
		int[] holder = null;

		boolean processCompleted = false;

		while (processCompleted == false) {
			try {
				size = getAmountOfLinesInTheScoresFile();
				addResults = new PrintStream(output);
				inputReader = new Scanner(input, "UTF-8");
				results = new int[size];
				while (inputReader.hasNextLine()) {
					currentLine = inputReader.nextInt();
					results[i] = currentLine;
					i++;
					if (i >= size) {
						System.out.println(size);
						holder = results;
						results = new int[size * 2];
						for (int j = 0; j < holder.length; j++) {
							results[j] = holder[j];
						}
					}
					if (i == size - 1) {
						Arrays.sort(results);
						for (int result : results) {
							addResults.println(result);
							System.out.println(result);
							processCompleted = true;
						}
					}
				}

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (inputReader != null) {
					inputReader.close();
				}
			}

		}
	}
	
	private int getAmountOfLinesInTheScoresFile() throws IOException {
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

}