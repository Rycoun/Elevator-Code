package com.techelevator.projects.dao;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDate;

public class JdbcEmployeeDaoTest extends BaseDaoTest {

    private static final Employee EMPLOYEE_1 =
        new Employee(1, 1, "First1", "Last1",
                LocalDate.parse("1981-01-01"), LocalDate.parse("2001-01-02"));
    private static final Employee EMPLOYEE_2 =
        new Employee(2, 2, "First2", "Last2",
                LocalDate.parse("1982-02-01"), LocalDate.parse("2002-02-03"));

    private JdbcEmployeeDao sut;
    private JdbcEmployeeDao invalidConnectionDao;

    @Before
    public void setup() {
        sut = new JdbcEmployeeDao(dataSource);
        invalidConnectionDao = new JdbcEmployeeDao(invalidDataSource);
    }

    @Test
    public void createEmployee_creates_employee() {
        Employee newEmployee = new Employee();
        newEmployee.setDepartmentId(1);
        newEmployee.setFirstName("Test");
        newEmployee.setLastName("Testerson");
        newEmployee.setBirthDate(LocalDate.parse("2021-02-21"));
        newEmployee.setHireDate(LocalDate.parse("2022-02-21"));

		Employee createdEmployee = sut.createEmployee(newEmployee);

        Assert.assertNotNull("createEmployee returned a null employee.", createdEmployee);
        Assert.assertTrue("createEmployee did not return a employee with id set.", createdEmployee.getId() > 0);
        Assert.assertEquals("createEmployee did not return an employee with the correct departmentId.", 1, newEmployee.getDepartmentId());
        Assert.assertEquals("createEmployee did not return an employee with the correct firstName.", "Test", newEmployee.getFirstName());
        Assert.assertEquals("createEmployee did not return an employee with the correct lastName.", "Testerson", newEmployee.getLastName());
        Assert.assertEquals("createEmployee did not return an employee with the correct birthDate.", LocalDate.parse("2021-02-21"), newEmployee.getBirthDate());
        Assert.assertEquals("createEmployee did not return an employee with the correct hireDate.", LocalDate.parse("2022-02-21"), newEmployee.getHireDate());

        // verify value was saved to database, retrieve it and compare values
        Employee retrievedEmployee = getEmployeeByIdForTestVerification(createdEmployee.getId());
        Assert.assertNotNull("createEmployee does not appear to have correctly persisted the newly created employee. It could not be found by id.", retrievedEmployee);
        assertEmployeesMatch("createEmployee does does not appear to have fully persisted the newly created employee. The retrieved employee is incorrect/incomplete.", createdEmployee, retrievedEmployee);
    }

    @Test
    public void createEmployee_throws_dao_exception_for_data_integrity_violation() {
        Employee newEmployee = new Employee();
        newEmployee.setDepartmentId(999); // non-existent department_id
        newEmployee.setFirstName("Test");
        newEmployee.setLastName("Testerson");
        newEmployee.setBirthDate(LocalDate.parse("2021-02-21"));
        newEmployee.setHireDate(LocalDate.parse("2022-02-21"));

        String methodName = "createEmployee";
        try {
            sut.createEmployee(newEmployee);
            Assert.fail(didNotThrowAnyExceptionForDataIntegrity(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForDataIntegrity(methodName));
        }
    }

    @Test
    public void updateEmployee_updates_employee() {
        Employee existingEmployee = new Employee();
        existingEmployee.setId(EMPLOYEE_2.getId());
        existingEmployee.setDepartmentId(1);
        existingEmployee.setFirstName("TestUpdate");
        existingEmployee.setLastName("UpdateTesterson");
        existingEmployee.setBirthDate(LocalDate.parse("2003-02-21"));
        existingEmployee.setHireDate(LocalDate.parse("2023-02-21"));

        Employee updatedEmployee = sut.updateEmployee(existingEmployee);
        Assert.assertNotNull("updateEmployee returned a null employee.", updatedEmployee);
        assertEmployeesMatch("updateEmployee returned an incorrect/incomplete employee.", updatedEmployee, existingEmployee);

        // verify value was saved to database, retrieve it and compare values
        Employee retrievedEmployee = getEmployeeByIdForTestVerification(EMPLOYEE_2.getId());
        assertEmployeesMatch("updateEmployee does not appear to have fully persisted the updated employee. The retrieved employee is incorrect/incomplete.", updatedEmployee, retrievedEmployee);
    }

    @Test
    public void updateEmployee_throws_dao_exception_for_data_integrity_violation() {
        Employee newEmployee = new Employee();
        newEmployee.setDepartmentId(999); // non-existent department_id
        newEmployee.setFirstName("Test");
        newEmployee.setLastName("Testerson");
        newEmployee.setBirthDate(LocalDate.parse("2021-02-21"));
        newEmployee.setHireDate(LocalDate.parse("2022-02-21"));

        String methodName = "updateEmployee";
        try {
            sut.updateEmployee(newEmployee);
            Assert.fail(didNotThrowAnyExceptionForDataIntegrity(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForDataIntegrity(methodName));
        }
    }

    @Test
    public void deleteEmployeeById_deletes_employee() {
        int recordsAffected = sut.deleteEmployeeById(EMPLOYEE_1.getId());
        Assert.assertEquals("deleteEmployeeById did not return the correct number of rows affected.", 1, recordsAffected);
        Employee retrievedEmployee = getEmployeeByIdForTestVerification(EMPLOYEE_1.getId());
        Assert.assertNull("deleteEmployeeById did not remove employee from database.", retrievedEmployee);
    } 

    @Test
    public void deleteEmployeesByDepartmentId_deletes_employees() {
        int recordsAffected = sut.deleteEmployeesByDepartmentId(EMPLOYEE_1.getDepartmentId());
        Assert.assertEquals("deleteEmployeesByDepartmentId did not return the correct number of rows affected.", 3, recordsAffected);
        Employee retrievedEmployee = getEmployeeByIdForTestVerification(EMPLOYEE_1.getId());
        Assert.assertNull("deleteEmployeesByDepartmentId did not remove employees from database.", retrievedEmployee);
    }

    @Test
    public void deleteEmployeeById_with_invalid_id_returns_zero_rows_affected() {
        int recordsAffected = sut.deleteEmployeeById(999); // non-existent employee_id
        Assert.assertEquals("deleteEmployeeById with invalid id did not return the correct number of rows affected.", 0, recordsAffected);
    }

    @Test
    public void deleteEmployeesByDepartmentId_with_invalid_id_returns_zero_rows_affected() {
        int recordsAffected = sut.deleteEmployeesByDepartmentId(999); // non-existent department_id
        Assert.assertEquals("deleteEmployeesByDepartmentId with invalid id did not return the correct number of rows affected.", 0, recordsAffected);
    }

    @Test
    public void employee_dao_get_methods_throw_dao_exception_for_invalid_connection() {
        String methodName = "";

        methodName = "getEmployeeById";
        try {
            invalidConnectionDao.getEmployeeById(1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "getEmployees";
        try {
            invalidConnectionDao.getEmployees();
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "getEmployeesByFirstNameLastName";
        try {
            invalidConnectionDao.getEmployeesByFirstNameLastName("First1", "Last1");
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "getEmployeesByProjectId";
        try {
            invalidConnectionDao.getEmployeesByProjectId(1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "getEmployeesWithoutProjects";
        try {
            invalidConnectionDao.getEmployeesWithoutProjects();
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }
    }

    @Test
    public void employee_dao_insert_update_delete_methods_throw_dao_exception_for_invalid_connection() {
        String methodName = "";

        methodName = "createEmployee";
        try {
            invalidConnectionDao.createEmployee(EMPLOYEE_1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "updateEmployee";
        try {
            invalidConnectionDao.updateEmployee(EMPLOYEE_1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "deleteEmployeeById";
        try {
            invalidConnectionDao.deleteEmployeeById(1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "deleteEmployeesByDepartmentId";
        try {
            invalidConnectionDao.deleteEmployeesByDepartmentId(1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }
    }

    public static void assertEmployeesMatch(String message, Employee expected, Employee actual) {
        Assert.assertEquals(message, expected.getId(), actual.getId());
        Assert.assertEquals(message, expected.getDepartmentId(), actual.getDepartmentId());
        Assert.assertEquals(message, expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(message, expected.getLastName(), actual.getLastName());
        Assert.assertEquals(message, expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(message, expected.getHireDate(), actual.getHireDate());
    }

    // test-specific implementation of getEmployeeById to be independent of DAO class
    private Employee getEmployeeByIdForTestVerification(int id) {
        Employee employee = null;
        String sql = "SELECT employee_id, department_id, first_name, last_name, birth_date, hire_date " +
                     "FROM employee WHERE employee_id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            Employee mappedEmployee = new Employee();
            mappedEmployee.setId(results.getInt("employee_id"));
            mappedEmployee.setDepartmentId(results.getInt("department_id"));
            mappedEmployee.setFirstName(results.getString("first_name"));
            mappedEmployee.setLastName(results.getString("last_name"));
            mappedEmployee.setBirthDate(results.getDate("birth_date").toLocalDate());
            mappedEmployee.setHireDate(results.getDate("hire_date").toLocalDate());
            employee = mappedEmployee;
        }
        return employee;
    }
}
