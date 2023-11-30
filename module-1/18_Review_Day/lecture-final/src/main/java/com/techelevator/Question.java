package com.techelevator;

public class Question {
    private final String questionText;  // What color is the sky?
    private final String[] answerOptions; // [ "Yellow", "Blue", "Green", "Purple" ]
    private final int correctAnswerIndex; // 1

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

    public boolean isCorrectAnswer(int answer) {
        return (answer - 1) == correctAnswerIndex;
    }

    public boolean isCorrectAnswer(String answer) { // "blue"
        if (answer == null) {
            return false;
        }

        String correctAnswer = answerOptions[correctAnswerIndex]; // "Blue"

        if (answer.equalsIgnoreCase(correctAnswer)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(questionText).append(System.lineSeparator());

        int questionIndex = 1;
        for (String answerOption : getAnswerOptions()) {
            builder.append(questionIndex + ". " + answerOption).append(System.lineSeparator());
            questionIndex++;
        }

        return builder.toString();
    }
}
