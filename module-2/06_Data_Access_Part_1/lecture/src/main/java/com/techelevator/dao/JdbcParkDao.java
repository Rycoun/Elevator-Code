package com.techelevator.dao;

import com.techelevator.model.Park;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class JdbcParkDao implements ParkDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcParkDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int getParkCount() {
        String sql = "select count(*) from park; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()) {
            return results.getInt("count");
        }
        return 0;
    }

    @Override
    public LocalDate getOldestParkDate() {
        String sql = "Select min(date_established) as oldest_date_established from park";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()) {
            Date oldestDateEstablished = results.getDate("oldest_date_established");
            if (oldestDateEstablished != null) {
                return oldestDateEstablished.toLocalDate();
            }
        }
        return null;
    }

    @Override
    public double getAverageParkArea() {
        return 0.0;
    }

    @Override
    public List<String> getParkNames() {
        List<String> allParkNames = new ArrayList<>();
        String sql = "select park_name from park order by asc";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            String park_name = results.getString("park_name");
            allParkNames.add(park_name);

        }
        return allParkNames;
    }

    @Override
    public Park getRandomPark() {
        return new Park();
    }

    @Override
    public List<Park> getParksWithCamping() {
        List<Park> allParksWithCamping = new ArrayList<>();
        String sql = "select * from park where has_camping = true";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Park park = mapRowToPark(results);
            allParksWithCamping.add(park);
        }
        return allParksWithCamping;
    }

    @Override
    public Park getParkById(int parkId) {
        return new Park();
    }

    @Override
    public List<Park> getParksByState(String stateAbbreviation) {
        List<Park> parkInState = new ArrayList<>();

        String sql = "select park.* from park join park_state using (park_id) where state_abbreviation = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stateAbbreviation);

        while (results.next()) {
            Park park = mapRowToPark(results);
            parkInState.add(park);
        }

        return parkInState;
    }

    @Override
    public List<Park> getParksByName(String name, boolean useWildCard) {

        return new ArrayList<>();
    }


    private Park mapRowToPark(SqlRowSet results) {
        int parkID = results.getInt("park_id");
        String parkName = results.getString("park_nane");
        java.sql.Date dateEstablished = results.getDate("date_established");
        LocalDate dateEstablishedParsed = null;
        if (dateEstablished != null) {
            dateEstablishedParsed = dateEstablished.toLocalDate();
        }
        double area = results.getDouble("area");
        boolean hasCamping = results.getBoolean("has_camping");

        Park park = new Park();
        park.setParkId(parkID);
        park.setParkName(parkName);
        park.setDateEstablished(dateEstablishedParsed);
        park.setArea(area);
        park.setHasCamping(hasCamping);

        return park;
    }
}
