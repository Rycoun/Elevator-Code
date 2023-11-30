package com.techelevator.projects;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.techelevator.projects.exception.DaoException;
import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.dao.DepartmentDao;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.dao.EmployeeDao;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.dao.ProjectDao;
import com.techelevator.projects.dao.JdbcDepartmentDao;
import com.techelevator.projects.dao.JdbcEmployeeDao;
import com.techelevator.projects.dao.JdbcProjectDao;
import com.techelevator.projects.view.Menu;

import javax.sql.DataSource;

public class EmployeeProjectsCLI {
	
	private static final String MAIN_MENU_OPTION_EMPLOYEES = "View and Manage Employees";
	private static final String MAIN_MENU_OPTION_DEPARTMENTS = "View and Manage Departments";
	private static final String MAIN_MENU_OPTION_PROJECTS = "View and Manage Projects";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_DEPARTMENTS, 
																	 MAIN_MENU_OPTION_EMPLOYEES, 
																	 MAIN_MENU_OPTION_PROJECTS, 
																	 MAIN_MENU_OPTION_EXIT };

	private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to main menu";

	private static final String DEPT_MENU_OPTION_ALL_DEPARTMENTS = "Show all departments";
	private static final String DEPT_MENU_OPTION_CREATE_DEPARTMENT = "Create new department";
	private static final String DEPT_MENU_OPTION_UPDATE_NAME = "Update department name";
	private static final String DEPT_MENU_OPTION_DELETE_DEPARTMENT = "Delete department";
	private static final String[] DEPARTMENT_MENU_OPTIONS = new String[] { DEPT_MENU_OPTION_ALL_DEPARTMENTS,
																		   DEPT_MENU_OPTION_CREATE_DEPARTMENT,
																		   DEPT_MENU_OPTION_UPDATE_NAME,
																		   DEPT_MENU_OPTION_DELETE_DEPARTMENT,
																		   MENU_OPTION_RETURN_TO_MAIN};
	


	private static final String EMPL_MENU_OPTION_ALL_EMPLOYEES = "Show all employees";
	private static final String EMPL_MENU_OPTION_SEARCH_BY_NAME = "Employee search by name";
	private static final String EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS = "Show employees without projects";
	private static final String EMPL_MENU_OPTION_CREATE_EMPLOYEE = "Create new employee";
	private static final String EMPL_MENU_OPTION_UPDATE_EMPLOYEE = "Update employee";
	private static final String EMPL_MENU_OPTION_DELETE_EMPLOYEE = "Delete employee";
	private static final String EMPL_MENU_OPTION_DELETE_EMPLOYEES_FROM_DEPARTMENT = "Delete all employees from a department";
	private static final String[] EMPL_MENU_OPTIONS = new String[] { EMPL_MENU_OPTION_ALL_EMPLOYEES,
																	 EMPL_MENU_OPTION_SEARCH_BY_NAME,
																	 EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS,
																	 EMPL_MENU_OPTION_CREATE_EMPLOYEE, 
																	 EMPL_MENU_OPTION_UPDATE_EMPLOYEE, 
																	 EMPL_MENU_OPTION_DELETE_EMPLOYEE, 
																	 EMPL_MENU_OPTION_DELETE_EMPLOYEES_FROM_DEPARTMENT,
																	 MENU_OPTION_RETURN_TO_MAIN};
	
	private static final String PROJ_MENU_OPTION_ACTIVE_PROJECTS = "Show all projects";
	private static final String PROJ_MENU_OPTION_PROJECT_EMPLOYEES = "Show project employees";
	private static final String PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT = "Assign an employee to a project";
	private static final String PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT = "Remove employee from project";
	private static final String PROJ_MENU_OPTION_CREATE_PROJECT = "Create new project";
	private static final String PROJ_MENU_OPTION_UPDATE_PROJECT = "Update project information";
	private static final String PROJ_MENU_OPTION_DELETE_PROJECT = "Delete project";
	private static final String[] PROJ_MENU_OPTIONS = new String[] { PROJ_MENU_OPTION_ACTIVE_PROJECTS,
																	 PROJ_MENU_OPTION_PROJECT_EMPLOYEES,
																	 PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT,
																	 PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT, 
																	 PROJ_MENU_OPTION_CREATE_PROJECT, 
																	 PROJ_MENU_OPTION_UPDATE_PROJECT,
																	 PROJ_MENU_OPTION_DELETE_PROJECT,
																	 MENU_OPTION_RETURN_TO_MAIN };
	
	private final Menu menu;
	private final DepartmentDao departmentDao;
	private final EmployeeDao employeeDao;
	private final ProjectDao projectDao;
	
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/EmployeeProjects");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		EmployeeProjectsCLI application = new EmployeeProjectsCLI(dataSource);
		application.run();
	}
	
	public EmployeeProjectsCLI(DataSource dataSource) {
		this.menu = new Menu(System.in, System.out);
		
		departmentDao = new JdbcDepartmentDao(dataSource);
		employeeDao = new JdbcEmployeeDao(dataSource);
		projectDao = new JdbcProjectDao(dataSource);
	}

	private void run() {
		displayApplicationBanner();
		boolean running = true;
		while(running) {
			printHeading("Main Menu");
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(choice.equals(MAIN_MENU_OPTION_DEPARTMENTS)) {
				handleDepartments();
			} else if(choice.equals(MAIN_MENU_OPTION_EMPLOYEES)) {
				handleEmployees();
			} else if(choice.equals(MAIN_MENU_OPTION_PROJECTS)) {
				handleProjects();
			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				running = false;
			}
		}
	}

	private void handleDepartments() {
		try {
			printHeading("Departments");
			String choice = (String)menu.getChoiceFromOptions(DEPARTMENT_MENU_OPTIONS);
			if(choice.equals(DEPT_MENU_OPTION_ALL_DEPARTMENTS)) {
				handleListAllDepartments();
			} else if(choice.equals(DEPT_MENU_OPTION_CREATE_DEPARTMENT)) {
				handleCreateDepartment();
			} else if(choice.equals(DEPT_MENU_OPTION_UPDATE_NAME)) {
				handleUpdateDepartmentName();
			} else if(choice.equals(DEPT_MENU_OPTION_DELETE_DEPARTMENT)){
				handleDeleteDepartment();
			}
		}
		catch (DaoException ex) {
			displayError("Error occurred: " + ex.getMessage());
		}
	}
	
	private void handleListAllDepartments() {
		printHeading("All Departments");
		List<Department> allDepartments = departmentDao.getDepartments();
		listDepartments(allDepartments);
	}
	
	private void listDepartments(List<Department> departments) {
		System.out.println();
		if(departments.size() > 0) {
			for(Department dept : departments) {
				System.out.println(dept);
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleCreateDepartment() {
		printHeading("Create New Department");
		String newDepartmentName = getUserInput("Enter new Department name");
		Department newDepartment = new Department();
		newDepartment.setName(newDepartmentName);
		newDepartment = departmentDao.createDepartment(newDepartment);
		System.out.println("\n*** "+newDepartment.getName()+" created ***");
	}
	
	private void handleUpdateDepartmentName() {
		printHeading("Update Department Name");
		List<Department> allDepartments = departmentDao.getDepartments();
		if(allDepartments.size() > 0) {
			System.out.println("\n*** Choose a Department ***");
			Department selectedDepartment = (Department)menu.getChoiceFromOptions(allDepartments.toArray());
			String newDepartmentName = getUserInput("Enter new Department name");
			selectedDepartment.setName(newDepartmentName);
			departmentDao.updateDepartment(selectedDepartment);
		} else {
			System.out.println("\n*** No results ***");
		}
	}
	
	private void handleDeleteDepartment() {
		printHeading("Delete Department");
		Department selectedDepartment = getDepartmentSelectionFromUser();

		departmentDao.deleteDepartmentById(selectedDepartment.getId());
		System.out.println("\n*** " + selectedDepartment.getName() + " deleted ***");
	}

	private Department getDepartmentSelectionFromUser() {
		System.out.println("Choose a project:");
		List<Department> allDepartments = departmentDao.getDepartments();
		return (Department)menu.getChoiceFromOptions(allDepartments.toArray());
	}

	private void handleEmployees() {
		try {
			printHeading("Employees");
			String choice = (String)menu.getChoiceFromOptions(EMPL_MENU_OPTIONS);
			if(choice.equals(EMPL_MENU_OPTION_ALL_EMPLOYEES)) {
				handleListAllEmployees();
			} else if(choice.equals(EMPL_MENU_OPTION_SEARCH_BY_NAME)) {
				handleEmployeeSearch();
			} else if(choice.equals(EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS)) {
				handleUnassignedEmployeeSearch();
			} else if(choice.equals(EMPL_MENU_OPTION_CREATE_EMPLOYEE)) {
				handleCreateEmployee();
			} else if(choice.equals(EMPL_MENU_OPTION_UPDATE_EMPLOYEE)) {
				handleUpdateEmployee();
			} else if(choice.equals(EMPL_MENU_OPTION_DELETE_EMPLOYEE)) {
				handleDeleteEmployee();
			} else if(choice.equals(EMPL_MENU_OPTION_DELETE_EMPLOYEES_FROM_DEPARTMENT)) {
				handleDeleteEmployeesFromDepartment();
			}
		}
		catch (DaoException ex) {
			displayError("Error occurred: " + ex.getMessage());
		}
	}

	private void handleListAllEmployees() {
		printHeading("All Employees");
		List<Employee> allEmployees = employeeDao.getEmployees();
		listEmployees(allEmployees);
	}

	private void handleEmployeeSearch() {
		printHeading("Employee Search");
		String firstNameSearch = getUserInput("Enter first name to search for");
		String lastNameSearch = getUserInput("Enter last name to search for");
		List<Employee> employees = employeeDao.getEmployeesByFirstNameLastName(firstNameSearch, lastNameSearch);
		listEmployees(employees);
	}

	private void handleUnassignedEmployeeSearch() {
		printHeading("Employees Without Projects");
		List<Employee> employees = employeeDao.getEmployeesWithoutProjects();
		listEmployees(employees);
	}

	private void handleCreateEmployee() {
		printHeading("Create New Employee");
		Employee newEmployee = new Employee();
		List<Department> allDepartments = departmentDao.getDepartments();
		if(allDepartments.size() > 0) {
		System.out.println("\n*** Choose a department ***");
		Department selectedDepartment = (Department)menu.getChoiceFromOptions(allDepartments.toArray());
		newEmployee.setDepartmentId(selectedDepartment.getId());
		}
		String newFirstName = getUserInput("Enter employee's first name");
		String newLastName = getUserInput("Enter employee's first name");
		String birthDate = getUserInput("Enter birth date (YYYY-MM-DD)");
		String hireDate = getUserInput("Enter hire date (YYYY-MM-DD)");
		newEmployee.setFirstName(newFirstName);
		newEmployee.setLastName(newLastName);
		newEmployee.setBirthDate(LocalDate.parse(birthDate));
		newEmployee.setHireDate(LocalDate.parse(hireDate));
		newEmployee = employeeDao.createEmployee(newEmployee);
		System.out.println("\n*** " + newEmployee.getFirstName() + " " + newEmployee.getLastName() + " created ***");
	}

	private void handleUpdateEmployee() {
		printHeading("Update Employee");
		List<Employee> allEmployees = employeeDao.getEmployees();
		if(allEmployees.size() > 0) {
			System.out.println("\n*** Choose an employee ***");
			Employee selectedEmployee = (Employee)menu.getChoiceFromOptions(allEmployees.toArray());

			List<Department> allDepartments = departmentDao.getDepartments();
			if(allDepartments.size() > 0) {
				System.out.println("\n*** Choose a department ***");
				Department selectedDepartment = (Department)menu.getChoiceFromOptions(allDepartments.toArray());
				selectedEmployee.setDepartmentId(selectedDepartment.getId());
			}
			String newFirstName = getUserInput("Enter employee's updated first name (leave blank to skip)");
			String newLastName = getUserInput("Enter employee's updated first name (leave blank to skip)");
			String birthDate = getUserInput("Enter updated birth date (YYYY-MM-DD)(leave blank to skip)");
			String hireDate = getUserInput("Enter updated hire date (YYYY-MM-DD) (leave blank to skip)");
			if(!newFirstName.equals("")) {
				selectedEmployee.setFirstName(newFirstName);
			}
			if(!newLastName.equals("")) {
				selectedEmployee.setLastName(newLastName);
			}
			if(!birthDate.equals("")) {
				selectedEmployee.setBirthDate(LocalDate.parse(birthDate));
			}
			if(!hireDate.equals("")) {
				selectedEmployee.setHireDate(LocalDate.parse(hireDate));
			}
			selectedEmployee = employeeDao.updateEmployee(selectedEmployee);
			System.out.println("\n*** " + selectedEmployee.getFirstName() + " " + selectedEmployee.getLastName() + " updated ***");			
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleDeleteEmployee() {
		printHeading("Delete Employee");
		List<Employee> allEmployees = employeeDao.getEmployees();
		if(allEmployees.size() > 0) {
			System.out.println("\n*** Choose an employee ***");
			Employee selectedEmployee = (Employee)menu.getChoiceFromOptions(allEmployees.toArray());
			employeeDao.deleteEmployeeById(selectedEmployee.getId());
			System.out.println("\n*** " + selectedEmployee.getFirstName() + " " + selectedEmployee.getLastName() + " deleted ***");			
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleDeleteEmployeesFromDepartment() {
		List<Department> allDepartments = departmentDao.getDepartments();
		if(allDepartments.size() > 0) {
			System.out.println("\n*** Choose a department ***");
			Department selectedDepartment = (Department)menu.getChoiceFromOptions(allDepartments.toArray());
			int deletedCount = employeeDao.deleteEmployeesByDepartmentId(selectedDepartment.getId());
			System.out.println("\n*** " + deletedCount + " employees deleted ***");			
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void listEmployees(List<Employee> employees) {
		System.out.println();
		if(employees.size() > 0) {
			for(Employee employee : employees) {
				System.out.println(employee);
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleProjects() {
		try {
			printHeading("Projects");
			String choice = (String)menu.getChoiceFromOptions(PROJ_MENU_OPTIONS);
			if(choice.equals(PROJ_MENU_OPTION_ACTIVE_PROJECTS)) {
				handleListActiveProjects();
			} else if(choice.equals(PROJ_MENU_OPTION_PROJECT_EMPLOYEES)) {
				handleProjectEmployeeList();
			} else if(choice.equals(PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT)) {
				handleEmployeeProjectAssignment();
			}  else if(choice.equals(PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT)) {
				handleEmployeeProjectRemoval();
			} else if(choice.equals(PROJ_MENU_OPTION_CREATE_PROJECT)) {
				handleCreateProject();
			} else if(choice.equals(PROJ_MENU_OPTION_UPDATE_PROJECT)) {
				handleUpdateProject();
			} else if(choice.equals(PROJ_MENU_OPTION_DELETE_PROJECT)) {
				handleDeleteProject();
			}
		}
		catch (DaoException ex) {
			displayError("Error occurred: " + ex.getMessage());
		}
	}

	private void handleListActiveProjects() {
		printHeading("Active Projects");
		List<Project> projects = projectDao.getProjects();
		listProjects(projects);
	}

	private void handleCreateProject() {
		printHeading("Create New Project");
		String newProjectName = getUserInput("Enter new Project name");
		String startDate = getUserInput("Enter start date (YYYY-MM-DD)");
		String endDate = getUserInput("Enter end date (YYYY-MM-DD)");
		Project newProject = new Project();
		newProject.setName(newProjectName);
		if(!startDate.equals("")) {
			newProject.setFromDate(LocalDate.parse(startDate));
		}
		if(!endDate.equals("")) {
			newProject.setToDate(LocalDate.parse(endDate));
		}
		newProject = projectDao.createProject(newProject);
		System.out.println("\n*** "+newProject.getName()+" created ***");
	}
	
	private void handleUpdateProject() {
		printHeading("Update Project");
		Project selectedProject = getProjectSelectionFromUser();
		String newProjectName = getUserInput("Enter updated project name (leave blank to skip)");
		String startDate = getUserInput("Enter updated start date (YYYY-MM-DD) (leave blank to skip)");
		String endDate = getUserInput("Enter updated end date (YYYY-MM-DD) (leave blank to skip)");
		if(!newProjectName.equals("")) {
			selectedProject.setName(newProjectName);
		}
		if(!startDate.equals("")) {
			selectedProject.setFromDate(LocalDate.parse(startDate));
		}
		if(!endDate.equals("")) {
			selectedProject.setToDate(LocalDate.parse(endDate));
		}
		selectedProject = projectDao.updateProject(selectedProject);
		System.out.println("\n*** "+selectedProject.getName()+" updated ***");
	}

	private void handleEmployeeProjectRemoval() {
		printHeading("Remove Employee From Project");
		
		Project selectedProject = getProjectSelectionFromUser();
		
		System.out.println("Choose an employee to remove:");
		List<Employee> projectEmployees = employeeDao.getEmployeesByProjectId(selectedProject.getId());
		if(projectEmployees.size() > 0) {
			Employee selectedEmployee = (Employee)menu.getChoiceFromOptions(projectEmployees.toArray());
			projectDao.unlinkProjectEmployee(selectedProject.getId(), selectedEmployee.getId());
			System.out.println("\n*** "+selectedEmployee+" removed from "+selectedProject+" ***");
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleEmployeeProjectAssignment() {
		printHeading("Assign Employee To Project");
		
		Project selectedProject = getProjectSelectionFromUser();
		
		System.out.println("Choose an employee to add:");
		List<Employee> allEmployees = employeeDao.getEmployees();
		Employee selectedEmployee = (Employee)menu.getChoiceFromOptions(allEmployees.toArray());
		
		projectDao.linkProjectEmployee(selectedProject.getId(), selectedEmployee.getId());
		System.out.println("\n*** "+selectedEmployee+" added to "+selectedProject+" ***");
	}

	private void handleDeleteProject() {
		printHeading("Delete Project");
		Project selectedProject = getProjectSelectionFromUser();

		projectDao.deleteProjectById(selectedProject.getId());
		System.out.println("\n*** " + selectedProject.getName() + " deleted ***");
	}
	
	private void handleProjectEmployeeList() {
		Project selectedProject = getProjectSelectionFromUser();
		List<Employee> projectEmployees = employeeDao.getEmployeesByProjectId(selectedProject.getId());
		listEmployees(projectEmployees);
	}

	private Project getProjectSelectionFromUser() {
		System.out.println("Choose a project:");
		List<Project> allProjects = projectDao.getProjects();
		return (Project)menu.getChoiceFromOptions(allProjects.toArray());
	}
	
	private void listProjects(List<Project> projects) {
		System.out.println();
		if(projects.size() > 0) {
			for(Project proj : projects) {
				System.out.println(proj);
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void printHeading(String headingText) {
		System.out.println("\n"+headingText);
		for(int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	private String getUserInput(String prompt) {
		System.out.print(prompt + " >>> ");
		return new Scanner(System.in).nextLine();
	}

	private void displayError(String message) {
		System.out.println();
		System.out.println("***" + message + "***");
	}

	private void displayApplicationBanner() {
		System.out.println(" ______                 _                         _____           _           _     _____  ____");
		System.out.println("|  ____|               | |                       |  __ \\         (_)         | |   |  __ \\|  _ \\");
		System.out.println("| |__   _ __ ___  _ __ | | ___  _   _  ___  ___  | |__) | __ ___  _  ___  ___| |_  | |  | | |_) |");
		System.out.println("|  __| | '_ ` _ \\| '_ \\| |/ _ \\| | | |/ _ \\/ _ \\ |  ___/ '__/ _ \\| |/ _ \\/ __| __| | |  | |  _ <");
		System.out.println("| |____| | | | | | |_) | | (_) | |_| |  __/  __/ | |   | | | (_) | |  __/ (__| |_  | |__| | |_) |");
		System.out.println("|______|_| |_| |_| .__/|_|\\___/ \\__, |\\___|\\___| |_|   |_|  \\___/| |\\___|\\___|\\__| |_____/|____/");
		System.out.println("                 | |             __/ |                          _/ |");
		System.out.println("                 |_|            |___/                          |__/");
		System.out.println();
	}
}
