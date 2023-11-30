package com.techelevator;

public class QuizNotFoundException extends Exception {
    private final String quizName;

    public QuizNotFoundException(String quizName) {
        super();
        this.quizName = quizName;
    }

    public QuizNotFoundException(String quizName, String message) {
        super(message);
        this.quizName = quizName;
    }

    public String getQuizName() {
        return quizName;
    }
}
