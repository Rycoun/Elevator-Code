package com.techelevator.movies.dao;

import com.techelevator.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcMovieDao implements MovieDao {

    private final String MOVIE_SELECT = "SELECT m.movie_id, m.title, m.overview, m.tagline, " +
            "m.poster_path, m.home_page, m.release_date, m.length_minutes, m.director_id, " +
            "m.collection_id FROM movie m ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Movie> getMovies() {

        List<Movie> movies = new ArrayList<>();
        String sqlGetMovies = MOVIE_SELECT;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMovies);
        while (results.next()) {
            movies.add(mapRowToMovie(results));
        }
        return movies;
    }

    @Override
    public Movie getMovieById(int id) {

        Movie movie = null;
        String sqlGetMovie = MOVIE_SELECT + " WHERE movie_id=?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMovie, id);
        if (results.next()) {
            movie = mapRowToMovie(results);
        }
        return movie;
    }

    @Override
    public List<Movie> getMoviesByTitle(String title, boolean useWildCard) {

        List<Movie> movies = new ArrayList<>();
        if (useWildCard) {
            title = "%" + title + "%";
        }
        String sqlGetMovies = MOVIE_SELECT + " WHERE title ILIKE ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMovies, title);
        while (results.next()) {
            movies.add(mapRowToMovie(results));
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesByDirectorNameAndBetweenYears(String directorName, int startYear,
                                                              int endYear, boolean useWildCard) {

        List<Movie> movies = new ArrayList<>();
        if (useWildCard) {
            directorName = "%" + directorName + "%";
        }
        // Adjust start and end dates to force years to be inclusively "between"
        LocalDate startDate = LocalDate.of(startYear - 1, 12, 31);
        LocalDate endDate = LocalDate.of(endYear + 1, 1, 1);
        String sqlGetMovies = MOVIE_SELECT + "JOIN person p ON m.director_id = p.person_id " +
                "WHERE p.person_name ILIKE ? " +
                "AND release_date BETWEEN ? AND ? " +
                "ORDER BY release_date";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMovies, directorName, startDate, endDate);
        while (results.next()) {
            movies.add(mapRowToMovie(results));
        }

        return movies;

    }
    private Movie mapRowToMovie(SqlRowSet sqlRowSet) {

        Movie movie = new Movie();
        movie.setId(sqlRowSet.getInt("movie_id"));
        movie.setTitle(sqlRowSet.getString("title"));
        movie.setOverview(sqlRowSet.getString("overview"));
        movie.setTagline(sqlRowSet.getString("tagline"));
        movie.setPosterPath(sqlRowSet.getString("poster_path"));
        movie.setHomePage(sqlRowSet.getString("home_page"));
        if (sqlRowSet.getDate("release_date") != null) {
            movie.setReleaseDate(sqlRowSet.getDate("release_date").toLocalDate());
        }
        movie.setLengthMinutes(sqlRowSet.getInt("length_minutes"));
        movie.setDirectorId(sqlRowSet.getInt("director_id"));
        movie.setCollectionId(sqlRowSet.getInt("collection_id"));
        return movie;
    }
}
