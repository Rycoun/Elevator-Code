package com.techelevator.projects.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Employee;

public class JdbcEmployeeDao implements EmployeeDao {

	private final String EMPLOYEE_SELECT = "SELECT e.employee_id, e.department_id, e.first_name, e.last_name, " +
				"e.birth_date, e.hire_date FROM employee e ";

	private final JdbcTemplate jdbcTemplate;

	public JdbcEmployeeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = null;
		String sql = EMPLOYEE_SELECT +
				" WHERE e.employee_id=?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if (results.next()) {
			employee = mapRowToEmployee(results);
		}

		return employee;
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> allEmployees = new ArrayList<>();
		String sql = EMPLOYEE_SELECT;

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Employee employeeResult = mapRowToEmployee(results);
			allEmployees.add(employeeResult);
		}

		return allEmployees;
	}

	@Override
	public List<Employee> getEmployeesByFirstNameLastName(String firstName, String lastName) {
		List<Employee> allEmployees = new ArrayList<>();
		String sql = EMPLOYEE_SELECT +
				" WHERE e.first_name ILIKE ? AND e.last_name ILIKE ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + firstName + "%", "%" + lastName + "%");
		while (results.next()) {
			Employee employeeResult = mapRowToEmployee(results);
			allEmployees.add(employeeResult);
		}

		return allEmployees;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(int projectId) {
		List<Employee> allEmployees = new ArrayList<>();
		String sql =  EMPLOYEE_SELECT +
				"JOIN project_employee pe ON e.employee_id = pe.employee_id " +
				"WHERE pe.project_id = ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
		while (results.next()) {
			Employee employeeResult = mapRowToEmployee(results);
			allEmployees.add(employeeResult);
		}

		return allEmployees;
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		List<Employee> allEmployees = new ArrayList<>();
		String sql = EMPLOYEE_SELECT +
				" WHERE e.employee_id NOT IN (SELECT DISTINCT employee_id FROM project_employee)";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Employee employeeResult = mapRowToEmployee(results);
			allEmployees.add(employeeResult);
		}

		return allEmployees;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		try {
			String sql = "insert into employee (department_id, first_name, last_name, birth_date, hire_date) " +
					"values (?, ?, ?, ?, ?) returning employee_id";

			SqlRowSet result = jdbcTemplate.queryForRowSet(sql,
					employee.getDepartmentId(),
					employee.getFirstName(),
					employee.getLastName(),
					Date.valueOf(employee.getBirthDate()),
					Date.valueOf(employee.getHireDate()));
			if (result.next()) {
				int employeeID = result.getInt("employee_id");
				return getEmployeeById(employeeID);
			} else {
				throw new DaoException("failed");
			}
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed due to data integrity violation");
		}
	}
	
	@Override
	public Employee updateEmployee(Employee employee) {
		try {
			String sql = "update employee set department_id = ?, first_name = ?, last_name = ?, " +
					"birth_date = ?, hire_date = ? where employee_id = ?";
			int rows = jdbcTemplate.update(sql,
					employee.getDepartmentId(),
					employee.getFirstName(),
					employee.getLastName(),
					Date.valueOf(employee.getBirthDate()),
					Date.valueOf(employee.getHireDate()),
					employee.getId()
					);
			if (rows == 0) {

				throw new DaoException("Failed");
			}
			return getEmployeeById(employee.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed due to data integrity violation");
		}
	}

	@Override
	public int deleteEmployeeById(int id) {
		try {
			String sql = "delete from employee where employee_id = ?";
			int rows = jdbcTemplate.update(sql, id);

			if (rows == 0) {
				return 0;
			}
			return rows;
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed due to data integrity violation");
		}
	}


	@Override
	public int deleteEmployeesByDepartmentId(int departmentId) {
		try {
			String sql = "delete from employee where department_id = ?";
			int rows = jdbcTemplate.update(sql, departmentId);

			if (rows == 0) {
				return 0;
			}
			return rows;
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed due to data integrity violation");
		}

		}

	private Employee mapRowToEmployee(SqlRowSet result) {
		Employee employee = new Employee();
		employee.setId(result.getInt("employee_id"));
		employee.setDepartmentId(result.getInt("department_id"));
		employee.setFirstName(result.getString("first_name"));
		employee.setLastName(result.getString("last_name"));
		employee.setBirthDate(result.getDate("birth_date").toLocalDate());
		employee.setHireDate(result.getDate("hire_date").toLocalDate());
		return employee;
	}
}
