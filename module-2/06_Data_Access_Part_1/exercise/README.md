# Data access part 1 exercise

For this exercise, you'll be responsible for implementing the Data Access Objects (DAOs) for a CLI application that manages information for collections, genres, movies, and persons. The purpose of this exercise is to practice writing application code that interacts with a database.

## Learning objectives

After completing this exercise, you'll understand:

* How to create database connections.
* How to execute SQL statements and use parameters.
* How the DAO pattern encapsulates database access logic.

## Evaluation criteria and functional requirements

* The project must not have any build errors.
* The unit tests pass as expected.
* Code is clean, concise, and readable.

You may use the provided CLI application to test your code. However, your goal is to make sure the tests pass.

## Getting started

1. If you haven't done so already, create the `MovieDB` database. The script to do this is `resources/postgresql/MovieDB.sql` at the top of your repository. You can run the script to create the database so that you have it for reference while working. Be aware, however, that the tests don't use that database. The tests use a temporary database with the same schema. You'll find the SQL for that temporary database in `src/test/resources/MovieDBTemp-data.sql`.
2. Open the exercise project in IntelliJ.
3. Run all the tests and note that they all fail.

## Step One: Explore starting code and database schema

Before you begin, review the provided classes in the `model` and `dao` packages.

You'll write your code to complete the data access methods in the `JdbcCollectionDao`, `JdbcGenreDao`, `JdbcMovieDao`, and `JdbcPersonDao` classes.

It's a good idea to also familiarize yourself with the database schema either by looking through the `MovieDB.sql` script or studying the ERD. The ERD filename is `MovieDB-ERD.png` and is in the same folder as `MovieDB.sql`.

## Step Two: Implement the `JdbcCollectionDao` methods

Complete the data access methods in `JdbcCollectionDao`. Refer to the documentation comments in the `CollectionDao` interface for the expected input and result of each method.

You can remove any existing `return` statement in the method when you start working on it.

After you complete this step, the tests in `JdbcCollectionDaoTests` pass.

## Step Three: Implement the `JdbcGenreDao` methods

Complete the data access methods in `JdbcGenreDao`. Refer to the documentation comments in the `GenreDao` interface for the expected input and result of each method.

You can remove any existing `return` statement in the method when you start working on it.

After you complete this step, the tests in `JdbcGenreDaoTests` pass.

## Step Four: Implement the `JdbcMovieDao` methods

Complete the data access methods in `JdbcMovieDao`. Refer to the documentation comments in the `MovieDao` interface for the expected input and result of each method.

You can remove any existing `return` statement in the method when you start working on it.

After you complete this step, the tests in `JdbcMovieDaoTests` pass.

## Step Five: Implement the `JdbcPersonDao` methods

Complete the data access methods in `JdbcPersonDao`. Refer to the documentation comments in the `PersonDao` interface for the expected input and result of each method.

You can remove any existing `return` statement in the method when you start working on it.

After you complete this step, the tests in `JdbcPersonDaoTests` pass.
