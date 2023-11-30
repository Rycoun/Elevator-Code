package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class FileQuizBuilder {

    public Quiz build(String quizName) throws QuizNotFound {
        Quiz quiz = new Quiz(quizName);


        File quizFile = new File(quizName);

        try (Scanner fileReader = new Scanner(quizFile)) {

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                Question question = parseLine(line);

                quiz.addQuestion(question);

            }

        } catch (FileNotFoundException e) {
            throw new QuizNotFound(quizName);

        }

            return quiz;

    }

    private Question parseLine(String line) {

        String[] lineParts = line.split("\\|" );
        String questionTest = lineParts[0];
        String[] answerOptions = new String[lineParts.length - 1];
        int correctAnswerIndex = -1;
        for (int i = 1; i < lineParts.length; i++) {
            String linePart = lineParts[i];
            if (linePart.endsWith("*")) {
                answerOptions[i-1] = linePart.substring(0, linePart.length()-1);
                correctAnswerIndex = i-1;

            } else {

                answerOptions[i - 1] = linePart;
            }
        }
        Question question = new Question(questionTest, answerOptions, correctAnswerIndex);

        return question;
    }


}
