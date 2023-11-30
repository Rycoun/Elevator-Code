package com.techelevator;
import java.util.*;

public class QuizMaker {

	// Use this scanner for all user input. Don't create additional Scanners with System.in
	private final Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) throws QuizNotFound {
		QuizMaker quizMaker = new QuizMaker();
		quizMaker.run();
	}

	public void run() throws QuizNotFound {
		/* Your code goes here */

		System.out.println("Enter the name of the quiz.");
		String quizName = userInput.nextLine();

		int numberCorrect = 0;

		FileQuizBuilder quizBuilder = new FileQuizBuilder();
		Quiz quiz = quizBuilder.build(quizName);

		for (Question question : quiz.getQuestions()) {

			System.out.println(question.getQuestionText());

			int questionIndex = 1;
			for (String answerOption : question.getAnswerOptions()) {
				System.out.println(answerOption + ". " + answerOption);
				questionIndex++;


			}
			System.out.println(question);
			int userAnswer = 0;
			while (true) {
				System.out.println("Enter your answer: ");

				try {
					userAnswer = Integer.parseInt(userInput.nextLine());

					if (userAnswer <= 0 || userAnswer > question.getAnswerOptions().length) {
						System.out.println("Please enter an interger between 1 and " + question.getAnswerOptions().length);
						continue;
					}
					break;
				} catch (NumberFormatException e) {
					System.out.println("PLease enter valid integer. ");
				}
				if (question.isCorrect(String.valueOf(userAnswer))) {
					System.out.println("Right!");
					numberCorrect++;
				} else {
					System.out.println("Sorry, Wrong ");

				}
				System.out.println();
				System.out.println("you answered " + numberCorrect + " correct out of" + quiz.getQuestions().size() + " questions. ");

			}


		}
	}

}
