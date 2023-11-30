package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private final String name;
    private final List<Question> questions = new ArrayList<>();

    public Quiz(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
