package com.techelevator.projects.dao;

import com.techelevator.projects.model.Project;

import java.util.List;

public interface ProjectDao {

	/**
	 * Get a project from the datastore that has the given id.
	 * If the id is not found, return null.
	 *
	 * @param id the id of the project to get from the datastore
	 * @return a filled out project object
	 */
	Project getProjectById(int id);

	/**
	 * Get a list of all projects.
	 * 
	 * @return all projects as project objects in a List
	 */
	List<Project> getProjects();

	/**
	 * Inserts a new project into the datastore.
	 *
	 * @param project the project object to insert
	 * @return the project object with its new id filled in
	 */
	Project createProject(Project project);

	/**
	 * Link a project to an employee
	 *
	 * @param projectId the project to put the employee on
	 * @param employeeId the employee to assign
	 */
	void linkProjectEmployee(int projectId, int employeeId);

	/**
	 * Unassign the project from an employee.
	 *
	 * @param projectId the project to remove the employee from
	 * @param employeeId the employee to remove
	 */
	void unlinkProjectEmployee(int projectId, int employeeId);

	/**
	 * Updates an existing project in the datastore.
	 *
	 * @param project the project object to update
	 * @return the project object with its updated fields
	 */
	Project updateProject(Project project);

	/**
	 * Removes a project from the datastore, which requires deleting
	 * records from multiple tables.
	 *
	 * @param id the id of the project to remove
	 * @return the number of projects deleted
	 */
	int deleteProjectById(int id);
}
