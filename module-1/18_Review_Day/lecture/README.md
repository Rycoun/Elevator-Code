# QuizMaker program (Challenge)

Create a quiz maker program that asks the user a question. You must prompt the user with multiple-choice answers and allow the user to enter their answer.

The program must read the questions from an input file during startup. The questions and answers in the file are pipe-delimited ("|"), and an asterisk ("*") marks the correct answer.

For example:
```
Question-1|answer-1|answer-2|correct-answer*|answer-4
```

An example of the file might look something like this:
```
What color is the sky?|Yellow|Blue*|Green|Red
What are Cleveland's odds of winning a championship?|Not likely*|Highly likely|Fat chance|Who cares??
```

**Tips**
* Use the `.split(String regex)` method to parse file input. The `.split` method divides a string based on the provided regular expression and returns the pieces as a `String[]`.
  * A regular expression, or regex, is a specially formatted string that defines a search pattern. Regular expressions use some characters for special meanings, including `|`.
  * To use a special character's literal value in Java you put a `\\` in front of it, so `.split("\\|")` divides your file input on each instance of a `|` pipe character.
* Create a class that can hold a quiz question, its available answers, and the correct answer.
* Try holding each quiz question in a list of quiz questions.

#### Step One: Ask user for input file and display first question

Ask the user a single question when the application starts. Don't show the asterisk in the list of choices.

Example
```
Enter the fully qualified name of the file to read in for quiz questions
[path-to-quiz-file]
What color is the sky?
1. Yellow
2. Blue
3. Green
4. Red

Your answer: 2
RIGHT!
```

#### Step Two: Ask user remaining questions and record correct answers

Go through all of the available quiz questions and ask the user each one sequentially. Record how many answers they got correct and print out the total at the end.

Example
```
What color is the sky?
1. Yellow
2. Blue
3. Green
4. Red

Your answer: 1
WRONG!

What are the Cleveland Browns' odds of winning a championship?
1. Not likely
2. Highly likely
3. Fat chance
4. Who cares??

Your answer: 1
RIGHT!

You got 1 answer(s) correct out of the 2 questions asked.
```

## Tips and tricks

### Practice creating command-line applications

Command-line applications can be a valuable asset to the teams that you work on. You may need to complete routine tasks with files at some scheduled intervals.

Learning how to create applications that can load data into systems, provide alerts when data is incorrect, or even automate other systems is an essential skill for developers to gain.

What tasks can you automate in your own life by applying the knowledge you now have about reading data from files?

### Get the file path from the user

Why might it be important to allow people to pass their own file path to the applications you write? How else might you get the path without explicitly asking your customer to provide the path?

### Learn how to read error messages

Learning how to read errors that occur when your applications fail is an important skill to develop as an application developer. When an error occurs, reading the error details is a great way to start the discovery process for diagnosing the problems that occurred.

### Read the documentation on File I/O

For more information on File I/O in Java, check out the [Java File Class API Docs](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html).
