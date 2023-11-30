package com.techelevator;

import java.io.*;
import java.util.Scanner;

public class FindAndReplace {

    // Use this scanner for all user input. Don't create additional Scanners with System.in
    private final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        FindAndReplace findAndReplace = new FindAndReplace();
        findAndReplace.run();
    }

    public void run() {


        System.out.println("What is the word?");
        String word = userInput.nextLine();



        System.out.println("What is the replacement word?");
        String replacementWord = userInput.nextLine();

        System.out.println("What is the file?");
        String sourceFilePath = userInput.nextLine();

        System.out.println("What is the destination file?");
        String destinationFilePath = userInput.nextLine();
        boolean result = findAndReplace(word, replacementWord, sourceFilePath, destinationFilePath);
        if (result) {
            System.out.println("Replacement worked yo.");
        } else {
            System.out.println("Replacement failed.");
        }
    }
    public boolean findAndReplace(String searchWord, String replacementWord, String sourceFilePath, String destinationFilePath) {
        try {
            File file = new File(sourceFilePath);
            if (!file.exists()) {
                System.out.println("File is not real .");
                return false;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath));


            String line;
            boolean replaced = false;
            while ((line = reader.readLine()) != null) {
                String replacedLine = line.replaceAll(searchWord, replacementWord);
                if (!replacedLine.equals(line)) {
                    replaced = true;
                }
                writer.write(replacedLine);
                writer.newLine();
            }
            reader.close();
            writer.close();

            if (!replaced) {
                System.out.println("No occurrences of the word found.");
                return false;
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred.");
            return false;
        }
    }
}

