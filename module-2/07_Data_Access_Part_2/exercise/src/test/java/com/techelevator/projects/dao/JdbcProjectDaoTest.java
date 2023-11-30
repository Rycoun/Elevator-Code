package com.techelevator.projects.dao;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcProjectDaoTest extends BaseDaoTest {
    private static final Employee EMPLOYEE_2 =
        new Employee(2, 2, "First2", "Last2",
                LocalDate.parse("1982-02-01"), LocalDate.parse("2002-02-03"));
    private static final Employee EMPLOYEE_3 =
        new Employee(3, 1, "First3", "Last3",
                LocalDate.parse("1983-03-01"), LocalDate.parse("2003-03-04"));

    private static final Project PROJECT_1 =
            new Project(1, "Project 1", LocalDate.parse("2000-01-02"), LocalDate.parse("2000-12-31"));
    private static final Project PROJECT_2 =
            new Project(2, "Project 2", null, null);

    private JdbcProjectDao sut;
    private JdbcProjectDao invalidConnectionDao;

    @Before
    public void setup() {
        sut = new JdbcProjectDao(dataSource);
        invalidConnectionDao = new JdbcProjectDao(invalidDataSource);
    }

    @Test
    public void createProject_creates_project() {
        Project newProject = new Project();
        newProject.setName("Project Ultima");
        newProject.setFromDate(LocalDate.of(2023, 02, 01));
        newProject.setToDate(LocalDate.of(2023, 04, 01));

        Project createdProject = sut.createProject(newProject);
        Assert.assertNotNull("createProject returned a null project.", createdProject);
        Assert.assertTrue("createProject did not return a project with id set.", createdProject.getId() > 0);
        Assert.assertEquals("createProject did not return a project with the correct name.", newProject.getName(), createdProject.getName());
        Assert.assertEquals("createProject did not return a project with the correct fromDate.", newProject.getFromDate(), createdProject.getFromDate());
        Assert.assertEquals("createProject did not return a project with the correct toDate.", newProject.getToDate(), createdProject.getToDate());

        // verify value was saved to database, retrieve it and compare values
        Project retrievedProject = getProjectByIdForTestVerification(createdProject.getId());
        Assert.assertNotNull("createProject does not appear to have correctly persisted the newly created project. It could not be found by id.", retrievedProject);
        assertProjectsMatch("createProject does not appear to have fully persisted the newly created project. The retrieved project is incorrect/incomplete.", createdProject, retrievedProject);
    }

    @Test
    public void createProject_throws_dao_exception_for_data_integrity_violation() {
        Project newProject = new Project();
        newProject.setName(PROJECT_1.getName()); // non-unique name

        String methodName = "createProject";
        try {
            sut.createProject(newProject);
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
    public void linkProjectEmployee_adds_employee_to_list_of_employees_for_project() {
        // Get list of employees before link
        int preLinkEmployeeCount = getProjectEmployeesForTestVerification(1).size();

        sut.linkProjectEmployee(1, 3);
        List<Employee> projectEmployees = getProjectEmployeesForTestVerification(1);
        int postLinkEmployeeCount = projectEmployees.size();

        Assert.assertEquals("linkProjectEmployee did not increase number of employees in project.", preLinkEmployeeCount + 1, postLinkEmployeeCount);
        assertProjectEmployeesMatch("linkProjectEmployee did not add correct employee to project.", EMPLOYEE_3, projectEmployees.get(1));
    }

    @Test
    public void unlinkProjectEmployee_removes_employee_from_list_of_employees_for_project() {
        // Get list of employees before unlink
        int preUnlinkEmployeeCount = getProjectEmployeesForTestVerification(2).size();

        sut.unlinkProjectEmployee(2, 3);
        List<Employee> projectEmployees = getProjectEmployeesForTestVerification(2);
        int postUnlinkEmployeeCount = projectEmployees.size();

        Assert.assertEquals("unlinkProjectEmployee did not decrease number of employees in project.", preUnlinkEmployeeCount - 1, postUnlinkEmployeeCount);
        assertProjectEmployeesMatch("unlinkProjectEmployee did not remove correct employee from project.", EMPLOYEE_2, projectEmployees.get(0));
    }
    
    @Test
    public void updateProject_updates_project() {
        Project existingProject = new Project();
        existingProject.setId(PROJECT_2.getId());
        existingProject.setName("Test Project Update");
        existingProject.setFromDate(LocalDate.parse("2003-02-21"));
        existingProject.setToDate(LocalDate.parse("2023-02-21"));

        Project updatedProject = sut.updateProject(existingProject);
        Assert.assertNotNull("updateProject returned a null project.", updatedProject);
        assertProjectsMatch("updateProject returned an incorrect/incomplete project.", updatedProject, existingProject);

        // verify value was saved to database, retrieve it and compare values
        Project retrievedProject = getProjectByIdForTestVerification(PROJECT_2.getId());
        assertProjectsMatch("updateProject does not appear to have fully persisted the updated project. The retrieved project is incorrect/incomplete.", updatedProject, retrievedProject);
    }

    @Test
    public void updateProject_throws_dao_exception_for_data_integrity_violation() {
        Project existingProject = new Project();
        existingProject.setId(PROJECT_2.getId());
        existingProject.setName(PROJECT_1.getName()); // non-unique name

        String methodName = "updateProject";
        try {
            sut.updateProject(existingProject);
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
    public void deleteProjectById_deletes_project() {
        int recordsAffected = sut.deleteProjectById(PROJECT_1.getId());
        Assert.assertEquals("deleteProjectById did not return the correct number of rows affected.", 1, recordsAffected);
        Project retrievedProject = getProjectByIdForTestVerification(PROJECT_1.getId());
        Assert.assertNull("deleteProjectById did not remove the project from database.", retrievedProject);
    }

    @Test
    public void deleteProjectById_with_invalid_id_returns_zero_rows_affected() {
        int recordsAffected = sut.deleteProjectById(999); // non-existent project_id
        Assert.assertEquals("deleteProjectById with invalid id did not return the correct number of rows affected.", 0, recordsAffected);
    }

    @Test
    public void project_dao_get_methods_throw_dao_exception_for_invalid_connection() {
        String methodName = "";

        methodName = "getProjectById";
        try {
            invalidConnectionDao.getProjectById(1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "getProjects";
        try {
            invalidConnectionDao.getProjects();
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
    public void project_dao_insert_update_delete_methods_throw_dao_exception_for_invalid_connection() {
        String methodName = "";

        methodName = "createProject";
        try {
            invalidConnectionDao.createProject(PROJECT_1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "linkProjectEmployee";
        try {
            invalidConnectionDao.linkProjectEmployee(1,1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "unlinkProjectEmployee";
        try {
            invalidConnectionDao.unlinkProjectEmployee(1,1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "updateProject";
        try {
            invalidConnectionDao.updateProject(PROJECT_1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }

        methodName = "deleteProjectById";
        try {
            invalidConnectionDao.deleteProjectById(1);
            Assert.fail(didNotThrowAnyExceptionForCannotGetConnection(methodName));
        } catch (DaoException e) {
            if (e.getMessage().equalsIgnoreCase(methodName + NOT_IMPLEMENTED_LONG_SUFFIX)) {
                Assert.fail(threwNotImplementedException(methodName));
            }
        } catch (Exception e) {
            Assert.fail(didNotThrowDaoExceptionForCannotGetConnection(methodName));
        }
    }

    private void assertProjectsMatch(String message, Project expected, Project actual) {
        Assert.assertEquals(message, expected.getId(), actual.getId());
        Assert.assertEquals(message, expected.getName(), actual.getName());
        Assert.assertEquals(message, expected.getFromDate(), actual.getFromDate());
        Assert.assertEquals(message, expected.getToDate(), actual.getToDate());
    }

    public static void assertProjectEmployeesMatch(String message, Employee expected, Employee actual) {
        Assert.assertEquals(message, expected.getId(), actual.getId());
        Assert.assertEquals(message, expected.getDepartmentId(), actual.getDepartmentId());
        Assert.assertEquals(message, expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(message, expected.getLastName(), actual.getLastName());
        Assert.assertEquals(message, expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(message, expected.getHireDate(), actual.getHireDate());
    }

    // test-specific implementation of getProjectById to be independent of DAO class
    private Project getProjectByIdForTestVerification(int id) {
        Project project = null;
        String sql = "SELECT project_id, name, from_date, to_date FROM project WHERE project_id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            Project mappedProject = new Project();
            mappedProject.setId(results.getInt("project_id"));
            mappedProject.setName(results.getString("name"));
            if(results.getDate("from_date") != null) {
                mappedProject.setFromDate(results.getDate("from_date").toLocalDate());
            }
            if(results.getDate("to_date") != null) {
                mappedProject.setToDate(results.getDate("to_date").toLocalDate());
            }
            project = mappedProject;
        }
        return project;
    }

    private List<Employee> getProjectEmployeesForTestVerification(int id) {
        List<Employee> projectEmployees = new ArrayList<>();
        String sql = "SELECT e.employee_id, e.department_id, e.first_name, e.last_name, e.birth_date, e.hire_date FROM employee e "+
                "JOIN project_employee pe ON e.employee_id = pe.employee_id "+
                "WHERE pe.project_id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            Employee mappedEmployee = new Employee();
            mappedEmployee.setId(results.getInt("employee_id"));
            mappedEmployee.setDepartmentId(results.getInt("department_id"));
            mappedEmployee.setFirstName(results.getString("first_name"));
            mappedEmployee.setLastName(results.getString("last_name"));
            mappedEmployee.setBirthDate(results.getDate("birth_date").toLocalDate());
            mappedEmployee.setHireDate(results.getDate("hire_date").toLocalDate());
            projectEmployees.add(mappedEmployee);
        }
        return projectEmployees;
    }
}
