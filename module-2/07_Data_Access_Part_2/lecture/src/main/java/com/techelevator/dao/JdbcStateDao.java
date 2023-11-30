package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.State;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcStateDao implements StateDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcStateDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public State getStateByAbbreviation(String stateAbbreviation) {
        State state = null;
        String sql = "SELECT state_abbreviation, state_name FROM state WHERE state_abbreviation = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stateAbbreviation);
            if (results.next()) {
                state = mapRowToState(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return state;
    }

    @Override
    public State getStateByCapital(int cityId) {
        State state = null;
        String sql = "SELECT state_abbreviation, state_name FROM state WHERE capital = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityId);
            if (results.next()) {
                state = mapRowToState(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return state;
    }

    @Override
    public List<State> getStates() {
        List<State> states = new ArrayList<>();
        String sql = "SELECT state_abbreviation, state_name FROM state;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                states.add(mapRowToState(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return states;
    }

    private State mapRowToState(SqlRowSet rowSet) {
        State state = new State();
        state.setStateAbbreviation(rowSet.getString("state_abbreviation"));
        state.setStateName(rowSet.getString("state_name"));
        return state;
    }
}
