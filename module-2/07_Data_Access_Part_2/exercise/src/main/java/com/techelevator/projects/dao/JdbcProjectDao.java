package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import javax.sql.DataSource;

import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.exception.DaoException;
import com.techelevator.projects.model.Project;

public class JdbcProjectDao implements ProjectDao {

	private final String PROJECT_SELECT = "SELECT p.project_id, p.name, p.from_date, p.to_date FROM project p";

	private final JdbcTemplate jdbcTemplate;

	public JdbcProjectDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Project getProjectById(int projectId) {
		Project project = null;
		String sql = PROJECT_SELECT +
				" WHERE p.project_id=?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
		if (results.next()) {
			project = mapRowToProject(results);
		}

		return project;
	}

	@Override
	public List<Project> getProjects() {
		List<Project> allProjects = new ArrayList<>();
		String sql = PROJECT_SELECT;

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Project projectResult = mapRowToProject(results);
			allProjects.add(projectResult);
		}

		return allProjects;
	}

	@Override
	public Project createProject(Project newProject) {
		try {
			String sql = "insert into project (name, from_date, to_date) values (?, ?, ?) returning project_id";
			SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, newProject.getName(), newProject.getFromDate() != null ? Date.valueOf(newProject.getFromDate()) : null, newProject.getToDate() != null ? Date.valueOf(newProject.getToDate()) : null
			);

			if (rowSet.next()) {
				int projectID = rowSet.getInt("project_id");
				return getProjectById(projectID);
			} else {
				throw new DaoException("failed to create");
			}
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Data integr check failed");
		} catch (CannotGetJdbcConnectionException e) {
			throw new DaoException("no connection");
		}
	}
	
	@Override
	public void linkProjectEmployee(int projectId, int employeeId) {
		try {
			String sql = "insert into project_employee (project_id, employee_id) values (?, ?);";
			int rows = jdbcTemplate.update(sql, projectId, employeeId);
			if (rows == 0) {
				throw new DaoException("link project fail");

			}
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Data inter failed");
		} catch (CannotGetJdbcConnectionException e) {
			throw new DaoException("no connection");
		}
	}

	@Override
	public void unlinkProjectEmployee(int projectId, int employeeId) {
		try {
			String sql = "delete from project_employee where project_id = ? and employee_id = ?";

			int rows = jdbcTemplate.update(sql, projectId, employeeId);
			if (rows == 0) {
				throw new DaoException("unlinkfail");

			}
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("failed due to data Integrity violation ");
		}



	}

	@Override
	public Project updateProject(Project project) {
		try {
			String sql = "update project set name = ?, from_date = ?, to_date = ? where project_id = ?";
			int rows = jdbcTemplate.update(sql, project.getName(), project.getFromDate() != null ? Date.valueOf(project.getFromDate()) : null, project.getToDate() != null ? Date.valueOf(project.getToDate()) : null, project.getId()
			);

			if (rows == 0){
				throw new DaoException("update failed");

			}
			return getProjectById(project.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("Failed due to data integrity violations");
		}

	}
	@Override
	public int deleteProjectById(int projectId) {
		try {
			String sql = "delete from project where project_id = ?";
			int rows = jdbcTemplate.update(sql, projectId);

			if (rows == 0) {
				throw new DaoException("delete failed");
			}
			return rows;
		} catch (DataIntegrityViolationException e) {
			throw new DaoException("failed due to data integrity violation");
		}


	}
	private Project mapRowToProject(SqlRowSet results) {
		Project project = new Project();
		project.setId(results.getInt("project_id"));
		project.setName(results.getString("name"));
		if (results.getDate("from_date") != null) {
			project.setFromDate(results.getDate("from_date").toLocalDate());
		}
		if (results.getDate("to_date") != null) {
			project.setToDate(results.getDate("to_date").toLocalDate());
		}
		return project;
	}

}
