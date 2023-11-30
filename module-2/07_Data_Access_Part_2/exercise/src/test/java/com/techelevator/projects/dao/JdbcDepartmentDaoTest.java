package com.techelevator.projects.dao;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcDepartmentDaoTest extends BaseDaoTest {

    private static final Department DEPARTMENT_1 = new Department(1, "Department 1");
    private static final Department DEPARTMENT_2 = new Department(2, "Department 2");

    private JdbcDepartmentDao sut;
    private JdbcDepartmentDao invalidConnectionDao;

    @Before
    public void setup() {
        sut = new JdbcDepartmentDao(dataSource);
        invalidConnectionDao = new JdbcDepartmentDao(invalidDataSource);
    }

    @Test
    public void createDepartment_creates_department() {
        Department newDepartment = new Department();
        newDepartment.setName("New Department Test");

        Department createdDepartment = sut.createDepartment(newDepartment);
        Assert.assertNotNull("createDepartment returned a null department.", createdDepartment);
        Assert.assertTrue("createDepartment did not return a department with id set.", createdDepartment.getId() > 0);
        Assert.assertEquals("createDepartment did not return a department with the correct name.", newDepartment.getName(), createdDepartment.getName());

        // verify value was saved to database, retrieve it and compare values
        Department retrievedDepartment = getDepartmentByIdForTestVerification(createdDepartment.getId());
        Assert.assertNotNull("createDepartment does not appear to have correctly persisted the newly created department. It could not be found by id.", retrievedDepartment);
        assertDepartmentsMatch("createDepartment does not appear to have fully persisted the newly created department. The retrieved department is incorrect/incomplete.",
                createdDepartment, retrievedDepartment);
    }

    @Test
    public void createDepartment_throws_dao_exception_for_data_integrity_violation() {
        Department newDepartment = new Department();
        newDepartment.setName(DEPARTMENT_1.getName()); // non-unique name

        String methodName = "createDepartment";
        try {
            sut.createDepartment(newDepartment);
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
    public void updateDepartment_updates_department() {
        Department existingDepartment = new Department();
        existingDepartment.setId(DEPARTMENT_2.getId());
        existingDepartment.setName("Test Updated Project Name");

        Department updatedDepartment = sut.updateDepartment(existingDepartment);
        Assert.assertNotNull("updateDepartment returned a null department.", updatedDepartment);
        assertDepartmentsMatch("updateDepartment returned an incorrect/incomplete department.", existingDepartment, updatedDepartment);

        // verify value was saved to database, retrieve it and compare values
        Department retrievedDepartment = getDepartmentByIdForTestVerification(DEPARTMENT_2.getId());
        assertDepartmentsMatch("updateDepartment does not appear to have fully persisted the updated department. The retrieved department is incorrect/incomplete.", updatedDepartment, retrievedDepartment);
    }

    @Test
    public void updateDepartment_throws_dao_exception_for_data_integrity_violation() {
        Department existingDepartment = new Department();
        existingDepartment.setId(DEPARTMENT_2.getId());
        existingDepartment.setName(DEPARTMENT_1.getName()); // non-unique name

        String methodName = "updateDepartment";
        try {
            sut.updateDepartment(existingDepartment);
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
    public void deleteDepartmentById_deletes_department() {
        int rowsAffected = sut.deleteDepartmentById(DEPARTMENT_1.getId());
        Assert.assertEquals("deleteDepartmentById did not return correct number of rows affected.", 1, rowsAffected);
        Department retrievedDepartment = getDepartmentByIdForTestVerification(DEPARTMENT_1.getId());
        Assert.assertNull("deleteDepartmentById did not remove department from database.", retrievedDepartment);
    }

    @Test
    public void deleteDepartmentById_with_invalid_id_returns_zero_rows_affected()
    {
        int rowsAffected = sut.deleteDepartmentById(999); // non-existent department_id
        Assert.assertEquals("deleteDepartmentById with invalid id did not return the correct number of rows affected", 0, rowsAffected);
    }

    @Test
    public void department_dao_get_methods_throw_dao_exception_for_invalid_connection() {
        String methodName = "";

        methodName = "getDepartmentById";
        try {
            invalidConnectionDao.getDepartmentById(1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "getDepartments";
        try {
            invalidConnectionDao.getDepartments();
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
    public void department_dao_insert_update_delete_methods_throw_dao_exception_for_invalid_connection() {
        String methodName = "";

        methodName = "createDepartment";
        try {
            invalidConnectionDao.createDepartment(DEPARTMENT_1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "updateDepartment";
        try {
            invalidConnectionDao.updateDepartment(DEPARTMENT_1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "deleteDepartmentById";
        try {
            invalidConnectionDao.deleteDepartmentById(1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }
    }

    private void assertDepartmentsMatch(String message, Department expected, Department actual) {
        Assert.assertEquals(message, expected.getId(), actual.getId());
        Assert.assertEquals(message, expected.getName(), actual.getName());
    }

    // test-specific implementation of getDepartmentById to be independent of DAO class
    private Department getDepartmentByIdForTestVerification(int id) {
        Department department = null;
        String sql = "SELECT department_id, name FROM department WHERE department_id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            Department mappedDepartment = new Department();
            mappedDepartment.setId(results.getInt("department_id"));
            mappedDepartment.setName(results.getString("name"));
            department = mappedDepartment;
        }
        return department;
    }
}
