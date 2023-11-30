package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Park;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcParkDao implements ParkDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcParkDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Park getParkById(int parkId) {
        Park park = null;
        String sql = "SELECT park_id, park_name, date_established, area, has_camping " +
                     "FROM park " +
                     "WHERE park_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
            if (results.next()) {
                park = mapRowToPark(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return park;
    }

    @Override
    public List<Park> getParksByState(String stateAbbreviation) {
        List<Park> parks = new ArrayList<>();
        String sql = "SELECT p.park_id, park_name, date_established, area, has_camping " +
                     "FROM park p " +
                     "JOIN park_state ps ON p.park_id = ps.park_id " +
                     "WHERE state_abbreviation = ? " +
                     "ORDER BY p.park_id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stateAbbreviation);
            while (results.next()) {
                parks.add(mapRowToPark(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return parks;
    }

    @Override
    public Park createPark(Park park) {
        Park newPark = null;
        String sql = "INSERT INTO park (park_name, date_established, area, has_camping) " +
                     "VALUES (?, ?, ?, ?) RETURNING park_id;";
        try {
            int newParkId = jdbcTemplate.queryForObject(sql, int.class,
                    park.getParkName(), park.getDateEstablished(), park.getArea(), park.getHasCamping());

            newPark = getParkById(newParkId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newPark;
    }

    @Override
    public Park updatePark(Park park) {
        Park updatedPark = null;
        String sql = "UPDATE park SET park_name = ?, date_established = ?, area = ?, has_camping = ? " +
                     "WHERE park_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, park.getParkName(), park.getDateEstablished(),
                    park.getArea(), park.getHasCamping(), park.getParkId());

            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedPark = getParkById(park.getParkId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedPark;
    }

    @Override
    public int deleteParkById(int parkId) {
        int numberOfRows = 0;
        String parkStateSql = "DELETE FROM park_state WHERE park_id = ?;";
        String parkSql = "DELETE FROM park WHERE park_id = ?";
        try {
            jdbcTemplate.update(parkStateSql, parkId);
            numberOfRows = jdbcTemplate.update(parkSql, parkId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return numberOfRows;
    }

    @Override
    public void linkParkState(int parkId, String stateAbbreviation) {
        String sql = "INSERT INTO park_state (park_id, state_abbreviation) VALUES (?, ?);";
        try {
            jdbcTemplate.update(sql, parkId, stateAbbreviation);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void unlinkParkState(int parkId, String stateAbbreviation) {
        String sql = "DELETE FROM park_state WHERE park_id = ? AND state_abbreviation = ?;";
        try {
            jdbcTemplate.update(sql, parkId, stateAbbreviation);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private Park mapRowToPark(SqlRowSet rowSet) {
        Park park = new Park();
        park.setParkId(rowSet.getInt("park_id"));
        park.setParkName(rowSet.getString("park_name"));
        park.setDateEstablished(rowSet.getDate("date_established").toLocalDate());
        park.setArea(rowSet.getDouble("area"));
        park.setHasCamping(rowSet.getBoolean("has_camping"));
        return park;
    }
}
