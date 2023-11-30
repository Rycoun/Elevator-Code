package com.techelevator.movies.dao;

import com.techelevator.movies.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcGenreDao implements GenreDao {

    private final String GENRE_SELECT = "SELECT g.genre_id, g.genre_name FROM genre g ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcGenreDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Genre> getGenres() {

        List<Genre> genres = new ArrayList<>();
        String sqlGetGenres = GENRE_SELECT;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetGenres);
        while (results.next()) {
            genres.add(mapRowToGenre(results));
        }
        return genres;
    }

    @Override
    public Genre getGenreById(int id) {

        Genre genre = null;
        String sqlGetGenre = GENRE_SELECT + " WHERE g.genre_id=?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetGenre, id);
        if (results.next()) {
            genre = mapRowToGenre(results);
        }
        return genre;
    }

    @Override
    public List<Genre> getGenresByName(String name, boolean useWildCard) {

        List<Genre> genres = new ArrayList<>();
        if (useWildCard) {
            name = "%" + name + "%";
        }
        String sqlGetGenres = GENRE_SELECT + " WHERE g.genre_name ILIKE ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetGenres, name);
        while (results.next()) {
            genres.add(mapRowToGenre(results));
        }
        return genres;
    }

    private Genre mapRowToGenre(SqlRowSet sqlRowSet) {

        Genre genre = new Genre();
        genre.setId(sqlRowSet.getInt("genre_id"));
        genre.setName(sqlRowSet.getString("genre_name"));
        return genre;
    }
}
