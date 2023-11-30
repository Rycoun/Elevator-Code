package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.City;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

public class JdbcCityDao implements CityDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCityDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int getCityCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS count FROM city;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if (results.next()) {
                count = results.getInt("count");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return count;
    }

    @Override
    public List<String> getCityNames() {
        List<String> cityNames = new ArrayList<>();
        String sql = "SELECT city_name FROM city ORDER by city_name ASC;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                cityNames.add(results.getString("city_name"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cityNames;
    }

    @Override
    public City getRandomCity() {
        City city = null;
        String sql = "SELECT * FROM city ORDER BY RANDOM() LIMIT 1;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if (results.next()) {
                city = mapRowToCity(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return city;
    }

    @Override
    public City getCityById(int cityId) {
        City city = null;
        String sql = "SELECT city_id, city_name, state_abbreviation, population, area " +
                     "FROM city " +
                     "WHERE city_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityId);
            if (results.next()) {
                city = mapRowToCity(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return city;
    }

    @Override
    public List<City> getCitiesByState(String stateAbbreviation) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT city_id, city_name, state_abbreviation, population, area " +
                     "FROM city " +
                     "WHERE state_abbreviation = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stateAbbreviation);
            while (results.next()) {
                cities.add(mapRowToCity(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cities;
    }

    @Override
    public City createCity(City city) {
        City newCity = null;
        String sql = "INSERT INTO city (city_name, state_abbreviation, population, area) " +
                     "VALUES (?, ?, ?, ?) RETURNING city_id;";
        try {
            int newCityId = jdbcTemplate.queryForObject(sql, Integer.class,
                    city.getCityName(), city.getStateAbbreviation(), city.getPopulation(), city.getArea());


            newCity = getCityById(newCityId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCity;
    }

    @Override
    public City updateCity(City city) {
        City updatedCity = null;
        String sql = "UPDATE city SET city_name = ?, state_abbreviation = ?, population = ?, area = ? " +
                     "WHERE city_id = ?;";
        try {
            int numberOfRows = jdbcTemplate.update(sql, city.getCityName(), city.getStateAbbreviation(), city.getPopulation(),
                    city.getArea(), city.getCityId());

            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedCity = getCityById(city.getCityId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedCity;
    }

    @Override
    public int deleteCityById(int cityId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM city WHERE city_id = ?;";
        try {
            numberOfRows = jdbcTemplate.update(sql, cityId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private City mapRowToCity(SqlRowSet rowSet) {
        City city = new City();
        city.setCityId(rowSet.getInt("city_id"));
        city.setCityName(rowSet.getString("city_name"));
        city.setStateAbbreviation(rowSet.getString("state_abbreviation"));
        city.setPopulation(rowSet.getInt("population"));
        city.setArea(rowSet.getDouble("area"));
        return city;
    }
}
