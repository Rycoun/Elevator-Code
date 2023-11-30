package com.techelevator;

import java.io.*;
import java.util.Scanner;

public class Lecture {

	public static void main(String[] args) {


		// create a PrintWriter object to write to a file
		File outputFile = new File("test.txt");
		try (PrintWriter printWriter = new PrintWriter(outputFile)) { // creates file if it does not exist. truncates if it does

			printWriter.println("Line 1");

			printWriter.flush(); // empty buffer manually. don't do this unless you have a good reason to

			printWriter.println("Line 2");
//			printWriter.println("Line 3");

		} catch (FileNotFoundException e) {

		}



		// append to a file if it exists. if it does not exist, the file will be created
		File outputFile2 = new File("test-append.txt");
		try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile2, true);
			 PrintWriter printWriter = new PrintWriter(fileOutputStream)) {

			printWriter.println("Line 1");
			printWriter.println("Line 2");

		} catch (IOException e) {

		}


		// write to a file while reading from a file
		File fileForReading = new File("numbers.txt");
		File fileForWriting = new File("doubled-numbers.txt");

		try (Scanner fileReader = new Scanner(fileForReading);
			 PrintWriter printWriter = new PrintWriter(fileForWriting);) {

			// while there are still lines to be read from numbers.txt
			while (fileReader.hasNextLine()) {

				String line = fileReader.nextLine(); // read the next line from numbers.txt
				int lineAsInteger = Integer.parseInt(line); // parse line as integer
				int doubledInteger = lineAsInteger * 2;
				printWriter.println(doubledInteger);

			}

		} catch (FileNotFoundException e) {

		}


		try {
			File testFile = new File("test.txt");


			long lengthOfFile = testFile.length();

			System.out.println("The length of the file is: " + lengthOfFile);

			testFile.delete();

			if (!testFile.exists()) {
				testFile.createNewFile();
			}


			File blahDirectory = new File("blah");

			blahDirectory.mkdir();

			File helloWorld = new File("blah/hello-world.txt");
			File goodbyeWorld = new File("blah/goodbye-world.txt");
			helloWorld.createNewFile();
			goodbyeWorld.createNewFile();

			for (File fileInBlah : blahDirectory.listFiles()) {
				System.out.println(fileInBlah.getName());
				fileInBlah.delete();
			}

			blahDirectory.delete();


		} catch (IOException e) {

		}

	}

}
