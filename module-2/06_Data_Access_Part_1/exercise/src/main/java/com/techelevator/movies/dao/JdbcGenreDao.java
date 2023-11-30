package com.techelevator.movies.dao;

import com.techelevator.movies.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import javax.sql.RowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcGenreDao implements GenreDao {


    private final JdbcTemplate jdbcTemplate;

    public JdbcGenreDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Genre> getGenres() {
        List<Genre> genres = new ArrayList<>();
        String sql = "select * from genres;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while (result.next());

return null;
    }

    @Override
    public Genre getGenreById(int id) {
        Genre genre = null;
        String sql = "Select * from genres where id = ?;";

        return genre;
    }

    @Override
    public List<Genre> getGenresByName(String name, boolean useWildCard) {
        List<Genre> genres = new ArrayList<>();
        String sql = "select * from genres where name ";
        if (useWildCard) {
            sql += "Like ?";
        }
        return  null;
    }

        public Genre mapRowToGenre(SqlRowSet rowSet) throws SQLException {
            Genre genre = new Genre();
            genre.setId(rowSet.getInt("genre_id"));
            genre.setName(rowSet.getString("genre_name"));
            return genre;

        }
    }

