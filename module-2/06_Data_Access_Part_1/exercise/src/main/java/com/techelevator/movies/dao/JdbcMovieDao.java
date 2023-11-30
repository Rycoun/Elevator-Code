package com.techelevator.movies.dao;

import com.techelevator.movies.model.Collection;
import com.techelevator.movies.model.Movie;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import javax.sql.RowSet;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcMovieDao implements MovieDao {


    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Movie> getMovies() {
        String sql = "string * from movie;";
        return jdbcTemplate.query(sql, this::mapMovie);

    }

    @Override
    public Movie getMovieById(int id) {
        String sql = "select * from movie where movie_id = ?;";
        List<Movie> movies = jdbcTemplate.queryForList(sql, Movie.class, id);
        if (movies.isEmpty()) {
            return null;
        } else {
            return movies.get(0);

        }
    }

    @Override
    public List<Movie> getMoviesByTitle(String title, boolean useWildCard) {
        String sql = "select * from movie where title ";
        if (useWildCard) {
            sql += "like ?";
            title = "%" + title + "%";

        } else {
            sql += "= ?";
        }
        return jdbcTemplate.queryForList(sql, Movie.class, title);
    }

    @Override
    public List<Movie> getMoviesByDirectorNameAndBetweenYears(String directorName, int startYear,
                                                              int endYear, boolean useWildCard) {
        String sql = "select movie.* from movie join person on person.person_id = movie.director_id where person_name ilike ? and release_date between ? and ? order by release_date asc;";

        if (useWildCard) {
            directorName = "%" + directorName + "%";
        }
        LocalDate startDate = LocalDate.of(startYear, 01, 01);
        LocalDate endDate = LocalDate.of(endYear, 12, 31);

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, directorName, startYear, endYear);

        List<Movie> movies = new ArrayList<>();
        while (results.next()) {
        Movie movie = new Movie();
        movie.setId(results.getInt("movie_id"));
        movie.setTitle(results.getString("title"));

        }
        return movies;
    }



    private Movie mapMovie (ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getInt("movie_id"));
        movie.setTitle(rs.getString("title"));
        movie.setOverview(rs.getString("overview"));
        movie.setTagline(rs.getString("tag_line"));
        movie.setPosterPath(rs.getString("poster_path"));
        movie.setHomePage(rs.getString("home_page"));
        movie.setReleaseDate(rs.getDate("release_date").toLocalDate());
        movie.setLengthMinutes(rs.getInt("length_minutes"));
        movie.setDirectorId(rs.getInt("director_id"));
        movie.setCollectionId(rs.getInt("collection_id"));
            return movie;
        }

        private List<Movie> mapRowSetToMovie(SqlRowSet rowSet) {
        List<Movie> movies = new ArrayList<>();
        while (rowSet.next()) {
            movies.add((Movie) mapRowSetToMovie(rowSet));

        }
        return movies;
    }


}




