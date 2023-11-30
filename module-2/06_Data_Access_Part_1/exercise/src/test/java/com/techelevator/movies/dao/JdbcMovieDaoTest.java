package com.techelevator.movies.dao;

import com.techelevator.movies.model.Movie;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcMovieDaoTest extends BaseDaoTest {

    private final Movie MOVIE_311 =
            new Movie(311,
                    "Once Upon a Time in America",
                    "A former Prohibition-era Jewish gangster returns to the Lower East Side of Manhattan over thirty years later, where he once again must confront the ghosts and regrets of his old life.",
                    "Crime, passion and lust for power.",
                    "https://image.tmdb.org/t/p/w500/i0enkzsL5dPeneWnjl1fCWm6L7k.jpg",
                    null,
                    LocalDate.of(1984, 5, 23),
                    229,
                    4385,
                    0
                    );

    private JdbcMovieDao sut;

    @Before
    public void setup() {
        sut = new JdbcMovieDao(dataSource);
    }

    @Test
    public void getMovies_returns_correct_number_of_movies() {

        List<Movie> movies = sut.getMovies();
        assertNotNull("getMovies returned a null list of movies.", movies);
        assertEquals("getMovies returned the wrong number of movies in the list.",
                44, movies.size());
    }

    @Test
    public void getMovieById_with_valid_id_returns_correct_movie() {

        Movie movie = sut.getMovieById(311);
        assertNotNull("getMovieById with valid id returned a null movie.", movie);
        assertMoviesMatch("getMovieById with valid id returned the incorrect/incomplete movie.", MOVIE_311, movie);
    }

    @Test
    public void getMovieById_with_invalid_id_returns_null_movie() {

        Movie movie = sut.getMovieById(0); // IDs begin with 1, cannot be 0
        assertNull("getMovieById with invalid id returned a movie rather than null.", movie);
    }

    @Test
    public void getMoviesByTitle_with_full_title_exact_match_returns_correct_number_of_movies() {

        List<Movie> movies = sut.getMoviesByTitle("Once upon a time in America", false);
        assertNotNull("getMoviesByTitle with full title exact match returned a null list of movies.", movies);
        assertEquals("getMoviesByTitle with full title exact match returned the wrong number of movies in the list.",
                1, movies.size());
    }

    @Test
    public void getMoviesByTitle_with_partial_title_exact_match_returns_correct_number_of_movies() {

        List<Movie> movies = sut.getMoviesByTitle("upon a time", false);
        assertNotNull("getMoviesByTitle with partial title exact match returned a null list of movies.", movies);
        assertEquals("getMoviesByTitle with partial title exact match returned the wrong number of movies in the list.",
                0, movies.size());
    }

    @Test
    public void getMoviesByTitle_with_empty_title_exact_match_returns_correct_number_of_movies() {

        List<Movie> movies = sut.getMoviesByTitle("", false);
        assertNotNull("getMoviesByTitle with empty title exact match returned a null list of movies.", movies);
        assertEquals("getMoviesByTitle with empty title exact match returned the wrong number of movies in the list.",
                0, movies.size());
    }

    @Test
    public void getMoviesByTitle_with_partial_title_wildcard_match_returns_correct_number_of_movies() {

        List<Movie> movies = sut.getMoviesByTitle("upon a time", true);
        assertNotNull("getMoviesByTitle with partial title wildcard match returned a null list of movies.", movies);
        assertEquals("getMoviesByTitle with partial title wildcard match returned the wrong number of movies in the list.",
                1, movies.size());
    }

    @Test
    public void getMoviesByTitle_with_empty_title_wildcard_match_returns_correct_number_of_movies() {

        List<Movie> movies = sut.getMoviesByTitle("", true);
        assertNotNull("getMoviesByTitle with empty title wildcard match returned a null list of movies.", movies);
        assertEquals("getMoviesByTitle with empty title wildcard match returned the wrong number of movies in the list.",
                44, movies.size());
    }

    @Test
    public void getMoviesByDirectorNameAndBetweenYears_with_valid_arguments_returns_correct_number_of_movies() {

        List<Movie> movies = sut.getMoviesByDirectorNameAndBetweenYears(
                "Alfred Hitchcock", 1950, 1959, true);
        assertNotNull("getMoviesByDirectorNameAndBetweenYears with valid arguments returned a null list of movies.", movies);
        assertEquals("getMoviesByDirectorNameAndBetweenYears with valid arguments returned the wrong number of movies in the list.",
                7, movies.size());
    }

    @Test
    public void getMoviesByDirectorNameAndBetweenYears_with_valid_arguments_and_wildcard_returns_correct_number_of_movies() {

        List<Movie> movies = sut.getMoviesByDirectorNameAndBetweenYears(
                "Chris", 1980, 2020, true);
        assertNotNull("getMoviesByDirectorNameAndBetweenYears with valid arguments and wildcard returned a null list of movies.", movies);
        assertEquals("getMoviesByDirectorNameAndBetweenYears with valid arguments and wildcard returned the wrong number of movies in the list.",
                2, movies.size());
    }

    private void assertMoviesMatch(String message, Movie expected, Movie actual) {

        assertEquals(message, expected.getId(), actual.getId());
        assertEquals(message, expected.getTitle(), actual.getTitle());
        assertEquals(message, expected.getTagline(), actual.getTagline());
        assertEquals(message, expected.getPosterPath(), actual.getPosterPath());
        assertEquals(message, expected.getHomePage(), actual.getHomePage());
        assertEquals(message, expected.getReleaseDate(), actual.getReleaseDate());
        assertEquals(message, expected.getLengthMinutes(), actual.getLengthMinutes());
        assertEquals(message, expected.getDirectorId(), actual.getDirectorId());
        assertEquals(message, expected.getCollectionId(), actual.getCollectionId());
    }
}
