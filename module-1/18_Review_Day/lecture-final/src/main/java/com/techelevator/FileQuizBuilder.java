package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileQuizBuilder implements QuizBuilder {

    public Quiz build(String quizName) throws QuizNotFoundException {
        Quiz quiz = new Quiz(quizName);

        File quizFile = new File(quizName);

        try (Scanner fileReader = new Scanner(quizFile)) {

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine(); // What color is the sky?|yellow|blue*|green|red

                Question question = parseLine(line);

                quiz.addQuestion(question);
            }

        } catch (FileNotFoundException e) {

            throw new QuizNotFoundException(quizName);
        }

        return quiz;
    }

    private Question parseLine(String line) {
        String[] lineParts = line.split("\\|"); // ["What color is the sky?", "yellow", "blue*", "green", "red"]

        String questionText = lineParts[0];
        String[] answerOptions = new String[lineParts.length - 1]; // ["yellow", "blue", "green", "red"]
        int correctAnswerIndex = -1;

        for (int i = 1; i < lineParts.length; i++) {

            String linePart = lineParts[i];

            if (linePart.endsWith("*")) {
                answerOptions[i-1] = linePart.substring(0, linePart.length()-1);
                correctAnswerIndex = i-1;
            } else {
                answerOptions[i-1] = linePart;
            }
        }

        // TODO: construct different objects depending on the type of question
        Question question = new Question(questionText, answerOptions, correctAnswerIndex);

        return question;
    }

}
