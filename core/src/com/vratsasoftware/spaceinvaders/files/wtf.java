package com.vratsasoftware.spaceinvaders.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class wtf {
 /* boolean processCompleted = false;

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
			}*/
	public static void main(String[] args) {
		File input = new File("C:\\Users\\velis\\Documents\\SpaceInvaders\\core\\src\\score.txt");
		Scanner inputFile = null;
		PrintStream outputFile = null;
		String current;
		String[] results;
		boolean processCompleted = false;
		int i = 0;
		try {
			int index = 0;
			while (processCompleted == false) {
			inputFile = new Scanner(input, "UTF-8");
			outputFile = new PrintStream(input, "UTF-8");
			while (inputFile.hasNextLine()) {
				index++;
			}
			 results = new String[index];
			while(inputFile.hasNextLine()) {
				results[i++] = inputFile.next();
			}
			if(i == index - 1) { 
				for (String string : results) {
					outputFile.append("\n" + string);
					processCompleted = true;
					outputFile.append("asdhas");
				}
				break;
			}
			// outputFile.append("|" + points);
			System.out.println(input.getAbsolutePath());
			}
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
