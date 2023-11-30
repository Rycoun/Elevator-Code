# Challenge Exercise: Server-Side APIs: Part 1 Exercise (Java)

Previously, you worked on testing and/or using a Web API that had been designed and developed. The Web API was a Random Number Guessing Game. Today, you'll create your own version of the same API.

### Import project into IntelliJ and explore starting code

Import this exercise into IntelliJ. After you've imported the project, review the starting code.

### Database

There is a folder named database that contains a script, `guess_number.sql`. 
Your first task is to create a new database and add tables necessary for this project.
To accomplish this, do the following:

1) Create a database named `guess_number`.
2) View the `guess_number.sql` script and complete the tasks outlined in there.
3) Run the `guess_number.sql` script to create the tables necessary for this project.

### Models

In the `model` package, there's a `Game.java` model that represents a game. The file is mostly empty. 
You will need to fill in the class matching the table you created in your database.

HINTS:
1) You should use a derived property for some of the fields instead of storing an additional column in the database. 
2) If you'd like to prevent any fields from being sent back to the client, use the annotation `@JsonIgnore` above the field.

### DAO

In the `dao` package, there's a `GuessNumberDao.java` interface that is finished for you. 
You will fill in the `JdbcGuessNumberDao.java` class, which implements the interface.
Each method should use SQL to query the database.

When creating a new game, you will need to generate a Random Number between a lower and upper bound that the 
player will need to guess. To facilitate this, we've provided a `RandomGenerator.java` class.

### Controllers

In the `controller` package, there's an `GuessNumberController.java` class. 

Within the class, you'll create the methods for the API. The controller class already contains the necessary `@RestController` annotation.

Use the Swagger Documentation from yesterday's exercise as a guide for the API methods that you'll build.
