package com.techelevator;

public class QuizNotFound extends Exception {
    private final String quizName;

    public QuizNotFound(String quizName) {
        super();
        this.quizName = quizName;
    }
    public QuizNotFound(String quizName, String message) {
        super(message);
        this.quizName = quizName;
    }


    public String getQuizName() {
        return quizName;

    }
}
