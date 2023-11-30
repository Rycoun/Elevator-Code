package com.techelevator;

import java.util.Scanner;

public class QuizMaker {

	// Use this scanner for all user input. Don't create additional Scanners with System.in
	private final Scanner userInput = new Scanner(System.in);
	private final QuizBuilder quizBuilder;

	public static void main(String[] args) {
		QuizBuilder quizBuilder = new FileQuizBuilder();
		QuizMaker quizMaker = new QuizMaker(quizBuilder);
		quizMaker.run();
	}

	public QuizMaker(QuizBuilder quizBuilder) {
		this.quizBuilder = quizBuilder;
	}

	public void run() {
		/* Your code goes here */

		Quiz quiz = getQuizToTake();

		int numberCorrect = 0;

		for (Question question : quiz.getQuestions()) {

			System.out.println(question);

			int usersAnswer = gatherUsersAnswer(question);

			if (question.isCorrectAnswer(usersAnswer)) {
				System.out.println("RIGHT!");
				numberCorrect++;
			} else {
				System.out.println("Sorry, incorrect.");
			}
		}

		System.out.println();
		System.out.println("You answered " + numberCorrect + " correctly out of " + quiz.getQuestions().size()  + " questions.");
	}

	private Quiz getQuizToTake() {
		while (true) {
			System.out.println("Enter the name of the quiz.");
			String quizName = userInput.nextLine();


			try {
				Quiz quiz = quizBuilder.build(quizName);
				return quiz;
			} catch (QuizNotFoundException e) {
				System.out.println("Sorry, quiz with name " + e.getQuizName() + " was not found.");
			}
		}
	}

	private int gatherUsersAnswer(Question question) {
		int usersAnswer;

		while (true) {
			System.out.print("Enter your answer: ");
			try {
				usersAnswer = Integer.parseInt(userInput.nextLine());

				if (usersAnswer <= 0  || usersAnswer > question.getAnswerOptions().length) {
					System.out.println("Please enter an integer between 1 and " + question.getAnswerOptions().length);
					continue;
				}

				break;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid integer.");
			}
		}

		return usersAnswer;
	}

}
