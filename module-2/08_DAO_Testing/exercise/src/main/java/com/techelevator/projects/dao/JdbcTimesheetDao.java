package com.techelevator.projects.dao;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Timesheet;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcTimesheetDao implements TimesheetDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTimesheetDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Timesheet getTimesheetById(int timesheetId) {
        Timesheet timesheet = null;
        String sql = "SELECT timesheet_id, employee_id, project_id, date_worked, hours_worked, billable, description " +
                "FROM timesheet " +
                "WHERE timesheet_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, timesheetId);
            if (results.next()) {
                timesheet = mapRowToTimesheet(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return timesheet;
    }

    @Override
    public List<Timesheet> getTimesheetsByEmployeeId(int employeeId) {
        List<Timesheet> timesheets = new ArrayList<>();
        String sql = "SELECT timesheet_id, employee_id, project_id, date_worked, hours_worked, billable, description " +
                "FROM timesheet " +
                "WHERE employee_id = ? " +
                "ORDER BY timesheet_id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, employeeId);
            if (results.next()) {
                Timesheet timesheet = mapRowToTimesheet(results);
                timesheets.add(timesheet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return timesheets;
    }

    @Override
    public List<Timesheet> getTimesheetsByProjectId(int projectId) {
        List<Timesheet> timesheets = new ArrayList<>();
        String sql = "SELECT timesheet_id, employee_id, project_id, date_worked, hours_worked, billable, description " +
                "FROM timesheet " +
                "WHERE employee_id = ? " +
                "ORDER BY timesheet_id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
            while (results.next()) {
                Timesheet timesheet = mapRowToTimesheet(results);
                timesheets.add(timesheet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return timesheets;
    }

    @Override
    public Timesheet createTimesheet(Timesheet newTimesheet) {
        int newId;
        String sql = "INSERT INTO timesheet (employee_id, project_id, date_worked, hours_worked, billable, description) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING timesheet_id;";
        try {
            newId = jdbcTemplate.queryForObject(sql, int.class, newTimesheet.getEmployeeId(), newTimesheet.getProjectId(),
                    newTimesheet.getDateWorked(), newTimesheet.getHoursWorked(), newTimesheet.isBillable(),
                    newTimesheet.getDescription());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return getTimesheetById(newId);
    }

    @Override
    public Timesheet updateTimesheet(Timesheet timesheet) {
        Timesheet updatedTimesheet = null;
        String sql = "UPDATE timesheet " +
                "SET employee_id = ?, project_id = ?, date_worked = ?, hours_worked = ?, description = ? " +
                "WHERE timesheet_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, timesheet.getEmployeeId(), timesheet.getProjectId(),
                    timesheet.getDateWorked(), timesheet.getHoursWorked(),
                    timesheet.getDescription(), timesheet.getTimesheetId());

            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedTimesheet = getTimesheetById(timesheet.getTimesheetId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedTimesheet;
    }

    @Override
    public int deleteTimesheetById(int timesheetId) {
        String sql = "DELETE FROM timesheet WHERE timesheet_id = ?";
        try {
            return jdbcTemplate.update(sql, timesheetId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public double getBillableHours(int employeeId, int projectId) {
        double billableHours = 0;
        String sql = "SELECT SUM(hours_worked) AS billable_hours " +
                "FROM timesheet " +
                "WHERE employee_id = ? AND project_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, employeeId, projectId);
            if (results.next()) {
                billableHours = results.getDouble("billable_hours");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return billableHours;
    }

    private Timesheet mapRowToTimesheet(SqlRowSet results) {
        Timesheet timesheet = new Timesheet();
        timesheet.setTimesheetId(results.getInt("timesheet_id"));
        timesheet.setEmployeeId(results.getInt("employee_id"));
        timesheet.setProjectId(results.getInt("project_id"));
        timesheet.setDateWorked(results.getDate("date_worked").toLocalDate());
        timesheet.setHoursWorked(results.getDouble("hours_worked"));
        timesheet.setBillable(results.getBoolean("billable"));
        timesheet.setDescription(results.getString("description"));
        return timesheet;
    }
}
