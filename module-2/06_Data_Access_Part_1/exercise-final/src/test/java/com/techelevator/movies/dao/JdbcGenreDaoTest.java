package com.techelevator.movies.dao;

import com.techelevator.movies.model.Genre;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcGenreDaoTest extends BaseDaoTest {

    private static final Genre GENRE_878 =
            new Genre(878, "Science Fiction");

    private JdbcGenreDao sut;

    @Before
    public void setup() {
        sut = new JdbcGenreDao(dataSource);
    }

    @Test
    public void getGenres_returns_correct_number_of_genres() {

        List<Genre> genres = sut.getGenres();
        assertNotNull("getGenres returned a null list of genres.", genres);
        assertEquals("getGenres returned the wrong number of genres in the list.",
                19, genres.size());
    }

    @Test
    public void getGenreById_with_valid_id_returns_correct_genre() {

        Genre genre = sut.getGenreById(878);
        assertNotNull("getGenreById with valid id returned a null genre.", genre);
        assertGenresMatch("getGenreById with valid id returned the incorrect/incomplete genre.", GENRE_878, genre);
    }

    @Test
    public void getGenreById_with_invalid_id_returns_null_genre() {

        Genre genre = sut.getGenreById(0); // IDs begin with 1, cannot be 0
        assertNull("getGenreById with invalid id returned a genre rather than null.", genre);
    }

    @Test
    public void getGenresByName_with_full_name_exact_match_returns_correct_number_of_genres() {

        List<Genre> genres = sut.getGenresByName("Science Fiction", false);
        assertNotNull("getGenresByName with full name exact match returned a null list of genres.", genres);
        assertEquals("getGenresByName with full name exact match returned the wrong number of genres in the list.",
                1, genres.size());
    }

    @Test
    public void getGenresByName_with_partial_name_exact_match_returns_correct_number_of_genres() {

        List<Genre> genres = sut.getGenresByName("ience Fict", false);
        assertNotNull("getGenresByName with partial name exact match returned a null list of genres.", genres);
        assertEquals("getGenresByName with partial name exact match returned the wrong number of genres in the list.",
                0, genres.size());
    }

    @Test
    public void getGenresByName_with_empty_name_exact_match_returns_correct_number_of_genres() {

        List<Genre> genres = sut.getGenresByName("", false);
        assertNotNull("getGenresByName with empty name exact match returned a null list of genres.", genres);
        assertEquals("getGenresByName with empty name exact match returned the wrong number of genres in the list.",
                0, genres.size());
    }

    @Test
    public void getGenresByName_with_partial_name_wildcard_match_returns_correct_number_of_genres() {

        List<Genre> genres = sut.getGenresByName("ience Fict", true);
        assertNotNull("getGenresByName with parital name wildcard match returned a null list of genres.", genres);
        assertEquals("getGenresByName with partial name wildcard match returned the wrong number of genres in the list.",
                1, genres.size());
    }

    @Test
    public void getGenresByName_with_empty_name_wildcard_match_returns_correct_number_of_genres() {

        List<Genre> genres = sut.getGenresByName("", true);
        assertNotNull("getGenresByName with empty name wildcard match returned a null list of genres.", genres);
        assertEquals("getGenresByName with empty name wildcard match returned the wrong number of genres in the list.",
                19, genres.size());
    }

    private void assertGenresMatch(String message, Genre expected, Genre actual) {

        assertEquals(message, expected.getId(), actual.getId());
        assertEquals(message, expected.getName(), actual.getName());
    }
}
