package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Department;

public class JdbcDepartmentDao implements DepartmentDao {

	private final String DEPARTMENT_SELECT = "SELECT d.department_id, d.name FROM department d ";
	
	private final JdbcTemplate jdbcTemplate;

	public JdbcDepartmentDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Department getDepartmentById(int id) {
		Department department = null;
		String sql = DEPARTMENT_SELECT +
				" WHERE d.department_id=?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if (results.next()) {
			department = mapRowToDepartment(results);
		}

		return department;
	}

	@Override
	public List<Department> getDepartments() {
		List<Department> departments = new ArrayList<>();
		String sql = DEPARTMENT_SELECT;

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			departments.add(mapRowToDepartment(results));
		}
		
		return departments;
	}

	@Override
	public Department createDepartment(Department department) {
		try {
			String sql = "insert into department (name) values (?) returning department_id";
			SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, department.getName());
			if (rowSet.next()){
				int departmentId = rowSet.getInt("department_id");
				return getDepartmentById(departmentId);
			} else {
				throw new DaoException("Failed");

			}
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed due to data Integrity violation");
		}
	}

	@Override
	public Department updateDepartment(Department department) {
		try {
			String sql = "update department set name = ? where department_id = ?";
			int rows = jdbcTemplate.update(sql, department.getName(), department.getId());

			if (rows == 0) {
				throw new DaoException("Failed");
			}
			return getDepartmentById(department.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed due to data Integrity violation");
		}
	}

	@Override
	public int deleteDepartmentById(int id) {
		try {
			String sql = "delete from department where department_id = ?";
			int rows = jdbcTemplate.update(sql, id);


			if (rows == 0) {
				return 0;
			}
			return rows;
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed due to data Integrity violation");
		}

	}

	private Department mapRowToDepartment(SqlRowSet results) {
		Department department = new Department();
		department.setId(results.getInt("department_id"));
		department.setName(results.getString("name"));
		return department;
	}

}
