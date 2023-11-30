package com.techelevator.dao;

import com.techelevator.model.City;
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
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		if (results.next()) {
			count = results.getInt("count");
		} 
        return count;
    }

    @Override
    public int getMostPopulatedCity() {
        int population = 0;
        String sql = "SELECT MAX(population) AS population FROM city;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		if (results.next()) {
			population = results.getInt("population");
		} 
        return population;
    }

    @Override
    public int getLeastPopulatedCity() {
        int population = 0;
        String sql = "SELECT MIN(population) AS population FROM city;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		if (results.next()) {
			population = results.getInt("population");
		} 
        return population;
    }

    @Override
    public double getAverageCityArea() {
        double avgArea = 0.0;
        String sql = "SELECT AVG(area) AS avg_area FROM city;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		if (results.next()) {
			avgArea = results.getDouble("avg_area");
		} 
        return avgArea;
    }

    @Override
    public List<String> getCityNames() {
        List<String> cityNames = new ArrayList<>();
        String sql = "SELECT city_name FROM city ORDER by city_name ASC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            cityNames.add(results.getString("city_name"));
        }
        return cityNames;
    }

    @Override
    public City getRandomCity() {
        City city = null;
        String sql = "SELECT * FROM city ORDER BY RANDOM() LIMIT 1;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()) {
            city = mapRowToCity(results);
        }
        return city;
    }

    @Override
    public City getCityById(int cityId) {
        City city = null;
        String sql = "SELECT city_id, city_name, state_abbreviation, population, area " +
                     "FROM city " +
                     "WHERE city_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityId);
        if (results.next()) {
            city = mapRowToCity(results);
        }
        return city;
    }

    @Override
    public List<City> getCitiesByState(String stateAbbreviation) {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT city_id, city_name, state_abbreviation, population, area " +
                     "FROM city " +
                     "WHERE state_abbreviation = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stateAbbreviation);
        while (results.next()) {
            cities.add(mapRowToCity(results));
        }
        return cities;
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
