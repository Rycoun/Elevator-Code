package com.techelevator;

public interface QuizBuilder {

    Quiz build(String quizName) throws QuizNotFoundException;
}
