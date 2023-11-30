package com.techelevator.projects.dao;

import com.techelevator.projects.model.Timesheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class JdbcTimesheetDaoTests extends BaseDaoTests {

    private static final Timesheet TIMESHEET_1 = new Timesheet(1, 1, 1,
            LocalDate.parse("2021-01-01"), 1.0, true, "Timesheet 1");
    private static final Timesheet TIMESHEET_2 = new Timesheet(2, 1, 1,
            LocalDate.parse("2021-01-02"), 1.5, true, "Timesheet 2");
    private static final Timesheet TIMESHEET_3 = new Timesheet(3, 2, 1,
            LocalDate.parse("2021-01-01"), 0.25, true, "Timesheet 3");
    private static final Timesheet TIMESHEET_4 = new Timesheet(4, 2, 2,
            LocalDate.parse("2021-02-01"), 2.0, false, "Timesheet 4");

    private JdbcTimesheetDao dao;


    @Before
    public void setup() {
        dao = new JdbcTimesheetDao(dataSource);
    }

    @Test
    public void getTimesheetById_with_valid_id_returns_correct_timesheet() {
        Timesheet resultTimesheet = dao.getTimesheetById(1);
        assertTimesheetsMatch(TIMESHEET_1, resultTimesheet);
    }

    @Test
    public void getTimesheetById_with_invalid_id_returns_null_timesheet() {
        Timesheet result = dao.getTimesheetById(5);
        assertNull(result);
    }

    @Test
    public void getTimesheetsByEmployeeId_with_valid_employee_id_returns_list_of_timesheets_for_employee() {
        List<Timesheet> timesheets = dao.getTimesheetsByEmployeeId(1);
        Assert.assertEquals(2, timesheets);
        assertTimesheetsMatch(TIMESHEET_1, timesheets.get(0));
        assertTimesheetsMatch(TIMESHEET_2, timesheets.get(1));
    }

    @Test
    public void getTimesheetsByProjectId_with_valid_id_returns_list_of_all_timesheets_for_project() {
        List<Timesheet> timesheets = dao.getTimesheetsByProjectId(1);
        Assert.assertEquals(2, timesheets.size());
        assertTimesheetsMatch(TIMESHEET_1, timesheets.get(0));
        assertTimesheetsMatch(TIMESHEET_2, timesheets.get(1));
    }

    @Test
    public void createTimesheet_creates_timesheet() {
        Timesheet newTimesheets = new Timesheet(5, 1, 2, LocalDate.parse("2023-10-23"), 2.5, true, "New Timesheet");
        dao.createTimesheet(newTimesheets);
        Timesheet theTimeSheet = dao.getTimesheetById(5);
        assertTimesheetsMatch(newTimesheets, theTimeSheet);
    }

    @Test
    public void updateTimesheet_updates_timesheet() {
        TIMESHEET_1.setHoursWorked(2.0);
        dao.updateTimesheet(TIMESHEET_1);
        Timesheet theTimesheet = dao.getTimesheetById(1);
        assertTimesheetsMatch(TIMESHEET_1, theTimesheet);
    }

    @Test
    public void deleteTimesheetById_deletes_timesheet() {
        dao.deleteTimesheetById(4);
        Timesheet theTimesheet = dao.getTimesheetById(4);
        assertNull(theTimesheet);
    }

    @Test
    public void getBillableHours_returns_correct_total() {
        double billedHours = dao.getBillableHours(1, 1);
        Assert.assertEquals(2.5, billedHours, 0.001);
    }

    private void assertTimesheetsMatch(Timesheet expected, Timesheet actual) {
        Assert.assertEquals(expected.getTimesheetId(), actual.getTimesheetId());
        Assert.assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        Assert.assertEquals(expected.getProjectId(), actual.getProjectId());
        Assert.assertEquals(expected.getDateWorked(), actual.getDateWorked());
        Assert.assertEquals(expected.getHoursWorked(), actual.getHoursWorked(), 0.001);
        Assert.assertEquals(expected.isBillable(), actual.isBillable());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

}
