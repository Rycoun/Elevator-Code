package com.techelevator.movies;

import java.util.List;
import java.util.Scanner;

import com.techelevator.movies.dao.*;
import com.techelevator.movies.model.*;
import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.movies.view.Menu;

import javax.sql.DataSource;

public class MovieDBCLI {
	
	private static final String MAIN_MENU_OPTION_COLLECTIONS = "Collections";
	private static final String MAIN_MENU_OPTION_GENRES = "Genres";
	private static final String MAIN_MENU_OPTION_MOVIES = "Movies";
	private static final String MAIN_MENU_OPTION_PERSONS = "Persons";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] {
			MAIN_MENU_OPTION_COLLECTIONS, MAIN_MENU_OPTION_GENRES, MAIN_MENU_OPTION_MOVIES,
			MAIN_MENU_OPTION_PERSONS, MAIN_MENU_OPTION_EXIT };

	private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to main menu";

	private static final String COLL_MENU_OPTION_ALL_COLLECTIONS = "Get all Collections";
	private static final String COLL_MENU_OPTION_COLLECTION_BY_ID = "Get Collection by ID";
	private static final String COLL_MENU_OPTION_COLLECTIONS_BY_NAME_WILDCARD = "Get Collections by name with wildcard";
	private static final String COLL_MENU_OPTION_COLLECTIONS_BY_NAME_EXACT = "Get Collections by exact name";
	private static final String[] COLL_MENU_OPTIONS = new String[] {COLL_MENU_OPTION_ALL_COLLECTIONS,
			COLL_MENU_OPTION_COLLECTION_BY_ID,
			COLL_MENU_OPTION_COLLECTIONS_BY_NAME_WILDCARD,
			COLL_MENU_OPTION_COLLECTIONS_BY_NAME_EXACT,
			MENU_OPTION_RETURN_TO_MAIN};

	private static final String GENR_MENU_OPTION_ALL_GENRES = "Get all Genres";
	private static final String GENR_MENU_OPTION_GENRE_BY_ID = "Get Genre by ID";
	private static final String GENR_MENU_OPTION_GENRES_BY_NAME_WILDCARD = "Get Genres by name with wildcard";
	private static final String GENR_MENU_OPTION_GENRES_BY_NAME_EXACT = "Get Genres by exact name";
	private static final String[] GENR_MENU_OPTIONS = new String[] {GENR_MENU_OPTION_ALL_GENRES,
			GENR_MENU_OPTION_GENRE_BY_ID,
			GENR_MENU_OPTION_GENRES_BY_NAME_WILDCARD,
			GENR_MENU_OPTION_GENRES_BY_NAME_EXACT,
			MENU_OPTION_RETURN_TO_MAIN};

	private static final String MOVI_MENU_OPTION_ALL_MOVIES = "Get all Movies";
	private static final String MOVI_MENU_OPTION_MOVIE_BY_ID = "Get Movie by ID";
	private static final String MOVI_MENU_OPTION_MOVIES_BY_TITLE_WILDCARD = "Get Movies by name with wildcard";
	private static final String MOVI_MENU_OPTION_MOVIES_BY_TITLE_EXACT = "Get Movies by exact name";
	private static final String MOVI_MENU_OPTION_MOVIES_BY_DIRECTOR_NAME_BETWEEN_YEARS =
		"Get Movies by Director Name between Years";
	private static final String[] MOVI_MENU_OPTIONS = new String[] {MOVI_MENU_OPTION_ALL_MOVIES,
			MOVI_MENU_OPTION_MOVIE_BY_ID,
			MOVI_MENU_OPTION_MOVIES_BY_TITLE_WILDCARD,
			MOVI_MENU_OPTION_MOVIES_BY_TITLE_EXACT,
			MOVI_MENU_OPTION_MOVIES_BY_DIRECTOR_NAME_BETWEEN_YEARS,
			MENU_OPTION_RETURN_TO_MAIN};

	private static final String PERSON_MENU_OPTION_ALL_PERSONS = "Get all Persons";
	private static final String PERSON_MENU_OPTION_PERSON_BY_ID = "Get Person by ID";
	private static final String PERSON_MENU_OPTION_PERSONS_BY_NAME_WILDCARD = "Get Persons by name with wildcard";
	private static final String PERSON_MENU_OPTION_PERSONS_BY_NAME_EXACT = "Get Persons by exact name";
	private static final String PERSON_MENU_OPTION_PERSONS_BY_COLLECTION_NAME = "Get Persons By Collection name";
	private static final String[] PERSON_MENU_OPTIONS = new String[] {PERSON_MENU_OPTION_ALL_PERSONS,
			PERSON_MENU_OPTION_PERSON_BY_ID,
			PERSON_MENU_OPTION_PERSONS_BY_NAME_WILDCARD,
			PERSON_MENU_OPTION_PERSONS_BY_NAME_EXACT,
			PERSON_MENU_OPTION_PERSONS_BY_COLLECTION_NAME,
			MENU_OPTION_RETURN_TO_MAIN};

	private final Menu menu;
	private final CollectionDao collectionDao;
	private final GenreDao genreDao;
	private final MovieDao movieDao;
	private final PersonDao personDao;

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/MovieDB");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		MovieDBCLI application = new MovieDBCLI(dataSource);
		application.run();
	}
	
	public MovieDBCLI(DataSource dataSource) {
		this.menu = new Menu(System.in, System.out);

		collectionDao = new JdbcCollectionDao(dataSource);
		genreDao = new JdbcGenreDao(dataSource);
		movieDao = new JdbcMovieDao(dataSource);
		personDao = new JdbcPersonDao(dataSource);
	}

	private void run() {
		boolean running = true;
		while (running) {
			printHeading("Main Menu");
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			switch (choice) {
				case MAIN_MENU_OPTION_COLLECTIONS:
					handleCollections();
					break;
				case MAIN_MENU_OPTION_GENRES:
					handleGenres();
					break;
				case MAIN_MENU_OPTION_MOVIES:
					handleMovies();
					break;
				case MAIN_MENU_OPTION_PERSONS:
					handlePersons();
					break;
				case MAIN_MENU_OPTION_EXIT:
					running = false;
					break;
			}
		}
	}

	private void handleCollections() {
		while (true) {
			printHeading("Collections");
			String choice = (String) menu.getChoiceFromOptions(COLL_MENU_OPTIONS);
			if (choice.equals(COLL_MENU_OPTION_ALL_COLLECTIONS)) {
				handleGetCollections();
			} else if (choice.equals(COLL_MENU_OPTION_COLLECTION_BY_ID)) {
				handleGetCollectionByID();
			} else if (choice.equals(COLL_MENU_OPTION_COLLECTIONS_BY_NAME_WILDCARD)) {
				handleGetCollectionByNameWildcard();
			} else if (choice.equals(COLL_MENU_OPTION_COLLECTIONS_BY_NAME_EXACT)) {
				handleGetCollectionByNameExact();
			} else if (choice.equals(MENU_OPTION_RETURN_TO_MAIN)) {
				break;
			}
		}
	}

	private void handleGetCollections() {
		printHeading("Get Collections");
		List<Collection> collections = collectionDao.getCollections();
		listCollections(collections);
	}

	private void handleGetCollectionByID() {
		printHeading("Get Collection by ID");
		int collectionID = Integer.parseInt(getUserInput("Enter Collection ID"));
		Collection collection = collectionDao.getCollectionById(collectionID);
		if (collection != null) {
			System.out.println(collection);
		}
		else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleGetCollectionByNameWildcard() {
		printHeading("Get Collection by Name (wildcard)");
		String collectionName = getUserInput("Enter Collection Name");
		List<Collection> collections = collectionDao.getCollectionsByName(collectionName, true);
		listCollections(collections);
	}

	private void handleGetCollectionByNameExact() {
		printHeading("Get Collection by Name (exact)");
		String collectionName = getUserInput("Enter Collection Name");
		List<Collection> collections = collectionDao.getCollectionsByName(collectionName, false);
		listCollections(collections);
	}

	private void listCollections(List<Collection> collections) {
		System.out.println();
		System.out.println("Collection count: " + collections.size());
		int limit = Math.min(collections.size(), 10);
		if (limit > 0) {
			for (int i = 0; i < limit; i++) {
				Collection collection = collections.get(i);
				System.out.println(collection);
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleGenres() {
		while (true) {
			printHeading("Genres");
			String choice = (String) menu.getChoiceFromOptions(GENR_MENU_OPTIONS);
			if (choice.equals(GENR_MENU_OPTION_ALL_GENRES)) {
				handleGetGenres();
			} else if (choice.equals(GENR_MENU_OPTION_GENRE_BY_ID)) {
				handleGetGenreByID();
			} else if (choice.equals(GENR_MENU_OPTION_GENRES_BY_NAME_WILDCARD)) {
				handleGetGenreByNameWildcard();
			} else if (choice.equals(GENR_MENU_OPTION_GENRES_BY_NAME_EXACT)) {
				handleGetGenreByNameExact();
			} else if (choice.equals(MENU_OPTION_RETURN_TO_MAIN)) {
				break;
			}
		}
	}

	private void handleGetGenres() {
		printHeading("Get Genres");
		List<Genre> genres = genreDao.getGenres();
		listGenres(genres);
	}

	private void handleGetGenreByID() {
		printHeading("Get Genre by ID");
		int genreID = Integer.parseInt(getUserInput("Enter Genre ID"));
		Genre genre = genreDao.getGenreById(genreID);
		if (genre != null) {
			System.out.println(genre);
		}
		else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleGetGenreByNameWildcard() {
		printHeading("Get Genre by Name (wildcard)");
		String genreName = getUserInput("Enter Genre Name");
		List<Genre> genres = genreDao.getGenresByName(genreName, true);
		listGenres(genres);
	}

	private void handleGetGenreByNameExact() {
		printHeading("Get Genre by Name (exact)");
		String genreName = getUserInput("Enter Genre Name");
		List<Genre> genres = genreDao.getGenresByName(genreName, false);
		listGenres(genres);
	}

	private void listGenres(List<Genre> genres) {
		System.out.println();
		System.out.println("Genre count: " + genres.size());
		int limit = Math.min(genres.size(), 10);
		if (limit > 0) {
			for (int i = 0; i < limit; i++) {
				Genre genre = genres.get(i);
				System.out.println(genre);
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleMovies() {
		while (true) {
			printHeading("Movies");
			String choice = (String) menu.getChoiceFromOptions(MOVI_MENU_OPTIONS);
			if (choice.equals(MOVI_MENU_OPTION_ALL_MOVIES)) {
				handleGetMovies();
			} else if (choice.equals(MOVI_MENU_OPTION_MOVIE_BY_ID)) {
				handleGetMovieByID();
			} else if (choice.equals(MOVI_MENU_OPTION_MOVIES_BY_TITLE_WILDCARD)) {
				handleGetMovieByTitleWildcard();
			} else if (choice.equals(MOVI_MENU_OPTION_MOVIES_BY_TITLE_EXACT)) {
				handleGetMovieByTitleExact();
			} else if (choice.equals(MOVI_MENU_OPTION_MOVIES_BY_DIRECTOR_NAME_BETWEEN_YEARS)) {
				handleGetMovieByDirectorNameBetweenYears();
			} else if (choice.equals(MENU_OPTION_RETURN_TO_MAIN)) {
				break;
			}
		}
	}

	private void handleGetMovies() {
		printHeading("Get Movies");
		List<Movie> movies = movieDao.getMovies();
		listMovies(movies);
	}

	private void handleGetMovieByID() {
		printHeading("Get Movie by ID");
		int movieID = Integer.parseInt(getUserInput("Enter Movie ID"));
		Movie movie = movieDao.getMovieById(movieID);
		if (movie != null) {
			System.out.println(movie);
		}
		else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleGetMovieByTitleWildcard() {
		printHeading("Get Movie by Title (wildcard)");
		String movieTitle = getUserInput("Enter Movie Title");
		List<Movie> movies = movieDao.getMoviesByTitle(movieTitle, true);
		listMovies(movies);
	}

	private void handleGetMovieByTitleExact() {
		printHeading("Get Movie by Title (exact)");
		String movieTitle = getUserInput("Enter Movie Title");
		List<Movie> movies = movieDao.getMoviesByTitle(movieTitle, false);
		listMovies(movies);
	}

	private void handleGetMovieByDirectorNameBetweenYears() {
		printHeading("Get Movie by Director Name between Years");
		String directorName = getUserInput("Enter Director Name");
		int beginYear = Integer.parseInt(getUserInput("Enter begin year"));
		int endYear = Integer.parseInt(getUserInput("Enter end year"));
		boolean wildcard = getUserInput("Use wildcard (t/f)?").equalsIgnoreCase("t");
		List<Movie> movies = movieDao.getMoviesByDirectorNameAndBetweenYears(directorName, beginYear, endYear, wildcard);
		listMovies(movies);
	}

	private void listMovies(List<Movie> movies) {
		System.out.println();
		System.out.println("Movie count: " + movies.size());
		int limit = Math.min(movies.size(), 10);
		if (limit > 0) {
			for (int i = 0; i < limit; i++) {
				Movie movie = movies.get(i);
				System.out.println(movie);
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handlePersons() {
		while (true) {
			printHeading("Persons");
			String choice = (String) menu.getChoiceFromOptions(PERSON_MENU_OPTIONS);
			if (choice.equals(PERSON_MENU_OPTION_ALL_PERSONS)) {
				handleGetPersons();
			} else if (choice.equals(PERSON_MENU_OPTION_PERSON_BY_ID)) {
				handleGetPersonByID();
			} else if (choice.equals(PERSON_MENU_OPTION_PERSONS_BY_NAME_WILDCARD)) {
				handleGetPersonByNameWildcard();
			} else if (choice.equals(PERSON_MENU_OPTION_PERSONS_BY_NAME_EXACT)) {
				handleGetPersonByNameExact();
			} else if (choice.equals(PERSON_MENU_OPTION_PERSONS_BY_COLLECTION_NAME)) {
				handleGetPersonByCollectionName();
			} else if (choice.equals(MENU_OPTION_RETURN_TO_MAIN)) {
				break;
			}
		}
	}

	private void handleGetPersons() {
		printHeading("Get Persons");
		List<Person> persons = personDao.getPersons();
		listPersons(persons);
	}

	private void handleGetPersonByID() {
		printHeading("Get Person by ID");
		int personID = Integer.parseInt(getUserInput("Enter Person ID"));
		Person person = personDao.getPersonById(personID);
		if (person != null) {
			System.out.println(person);
		}
		else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleGetPersonByNameWildcard() {
		printHeading("Get Person by Name (wildcard)");
		String personName = getUserInput("Enter Person Name");
		List<Person> persons = personDao.getPersonsByName(personName, true);
		listPersons(persons);
	}

	private void handleGetPersonByNameExact() {
		printHeading("Get Person by Name (exact)");
		String personName = getUserInput("Enter Person Name");
		List<Person> persons = personDao.getPersonsByName(personName, false);
		listPersons(persons);
	}

	private void handleGetPersonByCollectionName() {
		printHeading("Get Person by Collection Name");
		String personName = getUserInput("Enter Collection Name");
		boolean wildcard = getUserInput("Use wildcard (t/f)?").equalsIgnoreCase("t");
		List<Person> persons = personDao.getPersonsByCollectionName(personName, wildcard);
		listPersons(persons);
	}

	private void listPersons(List<Person> persons) {
		System.out.println();
		System.out.println("Person count: " + persons.size());
		int limit = Math.min(persons.size(), 10);
		if (limit > 0) {
			for (int i = 0; i < limit; i++) {
				Person person = persons.get(i);
				System.out.println(person);
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	private String getUserInput(String prompt) {
		System.out.print(prompt + " >>> ");
		return new Scanner(System.in).nextLine();
	}
}
