package com.techelevator.projects.dao;

import com.techelevator.projects.model.Employee;

import java.util.List;

public interface EmployeeDao {

	/**
	 * Get an employee from the datastore that has the given id.
	 * If the id is not found, return null.
	 * 
	 * @param id the id of the employee to get from the datastore
	 * @return an Employee object
	 */
	Employee getEmployeeById(int id);

	/**
	 * Gets all employees from the datastore and returns them in a List
	 * 
	 * @return all the employees as Employee objects in a List
	 */	
	List<Employee> getEmployees();

	/**
	 * Find all employees whose names contain the search strings. Returned employees should
	 * match both first and last name search strings. If a search string is blank,
	 * ignore it. If both strings are blank, return all employees.
	 * Be sure to use ILIKE for case-insensitive search matching!
	 * 
	 * @param firstName the string to search for in the first_name, ignore if blank
	 * @param lastName the string to search for in the last_name, ignore if blank
	 * @return all employees whose name matches as Employee objects in a List
	 */
	List<Employee> getEmployeesByFirstNameLastName(String firstName, String lastName);

	/**
	 * Get all of the employees that are on the project with the given id.
	 *
	 * @param projectId the project id to get the employees from
	 * @return all the employees assigned to that project as Employee objects in a List
	 */
	List<Employee> getEmployeesByProjectId(int projectId);

	/**
	 * Get all of the employees that aren't assigned to any project.
	 *
	 * @return all the employees not on a project as Employee objects in a List
	 */
	List<Employee> getEmployeesWithoutProjects();

	/**
	 * Inserts a new employee into the datastore.
	 *
	 * @param employee the employee object to insert
	 * @return the employee object with its new id filled in
	 */
	Employee createEmployee(Employee employee);

	/**
	 * Updates an existing employee in the datastore.
	 *
	 * @param employee the employee object to update
	 * @return the employee object with its updated fields
	 */
	Employee updateEmployee(Employee employee);

	/**
	 * Removes an employee from the datastore, which requires deleting
	 * records from multiple tables.
	 *
	 * @param id the id of the employee to remove
	 * @return the number of employees deleted
	 */
	int deleteEmployeeById(int id);

	/**
	 * Removes employees from the datastore, which requires deleting
	 * records from multiple tables.
	 *
	 * @param departmentId the id of the department to remove employees from
	 * @return the number of employees deleted
	 */
	int deleteEmployeesByDepartmentId(int departmentId);
}
