package com.techelevator;

public class Question {
    private final String questionText;
    private final String[] answerOptions;
    private final int correctAnswerIndex;

    public Question(String questionText, String[] answerOptions, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answerOptions = answerOptions;
        this.correctAnswerIndex = correctAnswerIndex;
    }


    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswerOptions() {
        return answerOptions;
    }

    public boolean isCorrect(String answer) {
        if (answer == null) {
        return false;


    }
    String correctAnswer = answerOptions[correctAnswerIndex];
    if (answer.equalsIgnoreCase(correctAnswer)) {
    return true;
    }
    return false;
}

@Override
public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(questionText).append(System.lineSeparator());;

    int questionIndex = 1;
    for (String answerOption : getAnswerOptions()) {
        builder.append(questionIndex + ". " + answerOption).append(System.lineSeparator());
        questionIndex++;

    }
    return builder.toString();
}
}