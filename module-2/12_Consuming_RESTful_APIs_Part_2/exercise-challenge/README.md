# Challenge Exercise: Consuming APIs: POST, PUT, and DELETE (Java) 

In this exercise, you'll work on testing a Web API that has been designed and developed.

## Step One: Start the server

Before starting, make sure the web API is up and running. Open the command line and navigate to the folder with this README in it. There should also be a .jar file.

Run the command: `java -jar guess-number*.jar`

To stop the server, you can enter `Ctrl+C`

## Step Two: Check out the API documentation

Open a browser and navigate to http://localhost:8080 

You should see a web page with API documentation. If not, revisit Step One to confirm that your server is running

## Step Three: Test the API using Postman

Attempt to test the API using Postman.

1. Begin by getting all games. You should see 2 games when starting for the first time. One of the games has been finished and the other has just started.
2. Try to get a particular game by id
3. Start a new game
4. Make a guess for the game you started. Notice the data returned tells you if your next guess should be higher or lower next time. 
5. Continue guessing until you guess the number.

## Step Four: Write the code for models, services, and the CLI

The application should allow for the user to 

1. Show the results of games / a game
2. Start a new game
3. Play an existing game
    - Make guesses until they are correct (tell the user higher or lower for each guess)
    - Quit if they don't want to guess any longer
4. Exit