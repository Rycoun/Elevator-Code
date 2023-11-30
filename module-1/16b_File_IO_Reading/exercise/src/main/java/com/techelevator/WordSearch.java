package com.techelevator;

import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class WordSearch {

	// Use this scanner for all user input. Don't create additional Scanners with System.in
	private final Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		WordSearch wordSearch = new WordSearch();
		wordSearch.run();
	}

	public void run() {
		/* Your code goes here */
		System.out.println("What is the name on file you're looking for? ");
		String path = userInput.nextLine();
		System.out.println("What is the word you are looking for? ");
		String word = userInput.nextLine();
		System.out.println("Should the word be case sensitive? Y/N");
		String input = userInput.nextLine();
		boolean caseSensitive = input.equalsIgnoreCase("Y");


		try {
			File file = new File(path);
			Scanner newFile = new Scanner(file);
			int num = 0;
			while (newFile.hasNextLine()) {
				num++;
				String result = newFile.nextLine();
				String search = caseSensitive ? result : result.toLowerCase();
				String compare = caseSensitive ? word : word.toLowerCase();
				if (search.contains(compare)) {
					System.out.println(num + ") " + result);
				}
		}
			newFile.close();
	} catch (FileNotFoundException e) {
			System.out.println("The input file was not found " + e.getMessage());
		}
	}

}
