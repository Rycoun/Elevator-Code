package com.techelevator;

import java.io.*;
import java.util.Scanner;

public class Lecture {

	public static void main(String[] args) {
		// object to write to file
		try (PrintWriter printWriter = new PrintWriter("test.txt")) { // creates file if non exist

			printWriter.println("Ling 1");

			printWriter.flush();

			printWriter.println("Ling 2");
			printWriter.println("Ling 3");


		} catch (FileNotFoundException e) {

		}

		File outputFile2 = new File("Test-append.txt");
		try (FileOutputStream fileOutput = new FileOutputStream(outputFile2, true);
			 PrintWriter printWriter = new PrintWriter(fileOutput)) {

			printWriter.println("Ling 1");


			printWriter.println("Ling 2");
			printWriter.println("Ling 3");

		} catch (IOException e) {

		}

		// write to a file while reading from a file
		File fileForReading = new File("numbers.txt");
		File fileForWriting = new File("double-numbers.txt");

		try (Scanner fileReader = new Scanner(fileForReading);
			 PrintWriter printWriter = new PrintWriter(fileForReading)) {

			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				int lineAsInt = Integer.parseInt(line);
				int doubledInt = lineAsInt * 2;
				printWriter.println(doubledInt);
			}

		} catch (FileNotFoundException e) {

		}
		try {
			File testFile = new File("Test.txt");
			Long lengthOfFile = testFile.length();
			System.out.println("the length is " + lengthOfFile);

			testFile.delete();

			if (!testFile.exists())
				testFile.createNewFile();


		File blahDir = new File("blah");
		blahDir.mkdir();

		for (File fileInBlah : blahDir.listFiles()) {
			System.out.println(fileInBlah.getName());
			fileInBlah.delete();
		}
		blahDir.delete();

	} catch (IOException e) {

		}

		}

}