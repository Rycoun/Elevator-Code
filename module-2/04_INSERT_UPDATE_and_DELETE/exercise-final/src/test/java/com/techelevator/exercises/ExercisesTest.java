package com.techelevator.exercises;

import java.io.File;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.swing.table.DefaultTableModel;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExercisesTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private static JdbcTemplate jdbcTemplate;
	private static String exerciseFolder;
	private static List<String> exerciseFiles = new ArrayList<>();

	private static SingleConnectionDataSource adminDataSource;
	private static JdbcTemplate adminJdbcTemplate;

	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() throws SQLException {
		adminDataSource = createDatasource("postgres");
		adminJdbcTemplate = new JdbcTemplate(adminDataSource);
		adminJdbcTemplate.update("CREATE DATABASE moviedbtemp;");

		dataSource = createDatasource("moviedbtemp");
		dataSource.setAutoCommit(false); //So we can rollback after each test.

		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("MovieDBTemp-data.sql"));

		jdbcTemplate = new JdbcTemplate(dataSource);

		exerciseFiles = getExerciseSqlFiles();
		if (exerciseFiles.size() == 0)
		{
			fail("Exercise folder and files not found. Please check that the exercise folder is in the same directory or in a directory above where these tests are running from.");
		}
	}

	@AfterClass
	public static void cleanup() {
		dataSource.destroy();
		adminJdbcTemplate.update("DROP DATABASE moviedbtemp;");
		adminDataSource.destroy();
	}

	@Before
	public void setup() {

	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	@Test
	public void exerciseProblem01() {
		int expectedRowsAffected = 1;

		String sqlVerify = "SELECT person_name, birthday FROM person WHERE person_name = 'Lisa Byway';";

		DefaultTableModel dtExpected = new DefaultTableModel();
		dtExpected.addColumn("person_name");
		dtExpected.addColumn("birthday");
		dtExpected.addRow(new Object[] {"Lisa Byway", java.sql.Date.valueOf("1984-04-15")});

		checkAnswerForProblem("01", expectedRowsAffected, sqlVerify, dtExpected);
	}

	@Test
	public void exerciseProblem02() {
		int expectedRowsAffected = 1;

		String sqlVerify = "SELECT title, overview, release_date, length_minutes FROM movie WHERE title = 'Euclidean Pi';";

		DefaultTableModel dtExpected = new DefaultTableModel();
		dtExpected.addColumn("title");
		dtExpected.addColumn("overview");
		dtExpected.addColumn("release_date");
		dtExpected.addColumn("length_minutes");
		dtExpected.addRow(new Object[] {"Euclidean Pi", "The epic story of Euclid as a pizza delivery boy in ancient Greece", java.sql.Date.valueOf("2015-03-14"), 194});

		checkAnswerForProblem("02", expectedRowsAffected, sqlVerify, dtExpected);
	}

	@Test
	public void exerciseProblem03() {
		int expectedRowsAffected = 1;

		String sqlVerify = "SELECT count(*) as count FROM movie_actor WHERE movie_id = 105 AND actor_id = 7036;";

		DefaultTableModel dtExpected = new DefaultTableModel();
		dtExpected.addColumn("count");
		dtExpected.addRow(new Object[] { 1L });

		checkAnswerForProblem("03", expectedRowsAffected, sqlVerify, dtExpected);	}

	@Test
	public void exerciseProblem04() {
		int expectedRowsAffected = 2;

		String sqlVerify = "SELECT (SELECT count(*) FROM genre WHERE genre_name = 'Sports') as genre_count, (SELECT count(*) FROM movie_genre WHERE genre_id IN (SELECT genre_id FROM genre WHERE genre_name = 'Sports') AND movie_id = 7214) as movie_genre_count;";

		DefaultTableModel dtExpected = new DefaultTableModel();
		dtExpected.addColumn("genre_count");
		dtExpected.addColumn("movie_genre_count");
		dtExpected.addRow(new Object[] { 1L, 1L });

		checkAnswerForProblem("04", expectedRowsAffected, sqlVerify, dtExpected);
	}

	@Test
	public void exerciseProblem05() {
		int expectedRowsAffected = 1;

		String sqlVerify = "SELECT title FROM movie WHERE movie_id = 11;";

		DefaultTableModel dtExpected = new DefaultTableModel();
		dtExpected.addColumn("title");
		dtExpected.addRow(new Object[] { "Star Wars: A New Hope" });

		checkAnswerForProblem("05", expectedRowsAffected, sqlVerify, dtExpected);
	}

	@Test
	public void exerciseProblem06() {
		int expectedRowsAffected = 3;

		String sqlVerify = "SELECT overview FROM movie WHERE length_minutes > 210 ORDER BY movie_id;";

		DefaultTableModel dtExpected = new DefaultTableModel();
		dtExpected.addColumn("overview");
		dtExpected.addRow(new Object[] { "This is a long movie. 229 minutes long." });
		dtExpected.addRow(new Object[] { "This is a long movie. 227 minutes long." });
		dtExpected.addRow(new Object[] { "This is a long movie. 317 minutes long." });

		checkAnswerForProblem("06", expectedRowsAffected, sqlVerify, dtExpected);
	}

	@Test
	public void exerciseProblem07() {
		int expectedRowsAffected = 14;

		String sqlVerify = "SELECT count(*) as actor_count FROM movie_actor WHERE movie_id = 299536;";

		DefaultTableModel dtExpected = new DefaultTableModel();
		dtExpected.addColumn("actor_count");
		dtExpected.addRow(new Object[] { 0L });

		checkAnswerForProblem("07", expectedRowsAffected, sqlVerify, dtExpected);
	}

	@Test
	public void exerciseProblem08() {
		int expectedRowsAffected = 6;

		String sqlVerify = "SELECT (SELECT count(*) FROM movie_actor WHERE actor_id = 37221) as actor_count, (SELECT count(*) FROM person WHERE person_id = 37221) as person_count;";

		DefaultTableModel dtExpected = new DefaultTableModel();
		dtExpected.addColumn("actor_count");
		dtExpected.addColumn("person_count");
		dtExpected.addRow(new Object[] { 0L, 0L });

		checkAnswerForProblem("08", expectedRowsAffected, sqlVerify, dtExpected);
	}

	@Test
	public void exerciseProblem09() {
		int expectedRowsAffected = 16;

		String sqlVerify = "SELECT (SELECT count(*) FROM movie_actor WHERE movie_id = 77) as actor_count, (SELECT count(*) FROM movie_genre WHERE movie_id = 77) as genre_count, (SELECT count(*) FROM movie WHERE movie_id = 77) as movie_count;";

		DefaultTableModel dtExpected = new DefaultTableModel();
		dtExpected.addColumn("actor_count");
		dtExpected.addColumn("genre_count");
		dtExpected.addColumn("movie_count");
		dtExpected.addRow(new Object[] { 0L, 0L, 0L });

		checkAnswerForProblem("09", expectedRowsAffected, sqlVerify, dtExpected);
	}

	@Test
	public void exerciseProblem10() {
		int expectedRowsAffected = 1;

		String sqlVerify = "SELECT home_page, profile_path FROM person WHERE person_id = 34035;";

		DefaultTableModel dtExpected = new DefaultTableModel();
		dtExpected.addColumn("home_page");
		dtExpected.addColumn("profile_path");
		dtExpected.addRow(new Object[] { "No image", null });

		checkAnswerForProblem("10", expectedRowsAffected, sqlVerify, dtExpected);
	}

	@Test
	public void exerciseProblem11() {
		int expectedRowsAffected = 2;

		String sqlVerify = "SELECT CASE WHEN director_id IS NOT NULL THEN 1 ELSE 0 END as director_set FROM movie WHERE movie_id = 367220;";

		DefaultTableModel dtExpected = new DefaultTableModel();
		dtExpected.addColumn("director_set");
		dtExpected.addRow(new Object[] { 1 });

		checkAnswerForProblem("11", expectedRowsAffected, sqlVerify, dtExpected);
	}

	@Test
	public void exerciseProblem12() {
		int expectedRowsAffected = 6;

		String sqlVerify = "SELECT (SELECT count(*) FROM collection WHERE collection_name = 'Bill Murray Collection') as collection_count, (SELECT count(*) FROM movie m JOIN movie_actor ma ON m.movie_id = ma.movie_id WHERE actor_id = 1532 AND collection_id = (SELECT collection_id FROM collection WHERE collection_name = 'Bill Murray Collection')) as movie_count;";

		DefaultTableModel dtExpected = new DefaultTableModel();
		dtExpected.addColumn("collection_count");
		dtExpected.addColumn("movie_count");
		dtExpected.addRow(new Object[] { 1L, 5L });

		checkAnswerForProblem("12", expectedRowsAffected, sqlVerify, dtExpected);
	}

	private void checkAnswerForProblem(String problemNumber, int expectedRowsAffected, String sqlVerify, DefaultTableModel dtExpected) {
		String sqlActual = getStudentQuery(problemNumber);

		sqlActual = sqlActual.replaceAll("--[^\n]*(\n|$)", "");
		assertFalse("No query found for this exercise. Make sure you've saved your changes to the exercise file.", sqlActual.isBlank());

		//Using batchUpdate because several exercises require multiple statements.
		String[] splitStatements = sqlActual.split(";");
		int actualRowsAffected = Arrays.stream(jdbcTemplate.batchUpdate(splitStatements)).sum();
		if (actualRowsAffected < expectedRowsAffected) {
			fail("Your query didn't affect enough rows");
		} else if (actualRowsAffected > expectedRowsAffected) {
			fail("Your query affected too many rows");
		}

		SqlRowSet sqlRSVerify = jdbcTemplate.queryForRowSet((sqlVerify));

		// compare expected and actual data
		compareData(dtExpected, sqlRSVerify);
	}

	private void compareData(DefaultTableModel dtExpected, SqlRowSet sqlRSVerify) {
		int colCountExpected = dtExpected.getColumnCount();
		List<String> colNamesExpected = new ArrayList<>();

		for (int i = 0; i < colCountExpected; i++) {
			colNamesExpected.add(dtExpected.getColumnName(i));
		}

		sqlRSVerify.last();
		assertEquals("Couldn't find the expected data after running your SQL. Make sure you've spelled everything correctly.",
				dtExpected.getRowCount(), sqlRSVerify.getRow());
		sqlRSVerify.beforeFirst();

		while (sqlRSVerify.next()){
			for (String colNameExpected : colNamesExpected) {
				int colIndex = colNamesExpected.indexOf((colNameExpected));
				int rowIndex = sqlRSVerify.getRow();
				Object valExpected = dtExpected.getValueAt(rowIndex - 1, colIndex);
				Object valVerify = sqlRSVerify.getObject(colNameExpected);
				assertEquals("Data returned doesn't match data expected for row " + rowIndex + " in column '" + colNameExpected + "'.",
						valExpected, valVerify);
			}
		}
	}

	private String getStudentQuery(String problemNumber) {
		String exerciseFilePath = exerciseFiles.stream()
				.filter( item ->  item.startsWith(problemNumber) ).findFirst()
				.orElse(null);
		String sql = "";

		if (exerciseFilePath == null) {
			fail("No exercise file found. Check that the file name begins with " + problemNumber + ".");
		}

		File exerciseFile = new File(exerciseFolder + "/" + exerciseFilePath);
		if (!exerciseFile.exists()) {
			fail("Exercise file doesn't exist.");
		}

		try {
			sql = Files.readString(exerciseFile.toPath());
		}
		catch (Exception e) {
			fail("Exception occurred reading exerciseFile: " + e.getMessage());
		}

		sql = sql.trim();

		return sql;
	}

	private static List<String> getExerciseSqlFiles() {
		String folderToFind = "Exercises";
		String currPath = System.getProperty("user.dir");
		List<String> exerFiles = new ArrayList<>();

		if (currPath.contains("\\")) {
			currPath = currPath.replace("\\", "/");
		}

		while (exerFiles.size() == 0) {
			File directoryPath = new File(currPath);

			if (directoryPath.isDirectory()) {
				File[] directories = directoryPath.listFiles((dir, name) -> name.endsWith(folderToFind));

				if (directories != null && directories.length == 1) {
					File directory = directories[0];

					exerciseFolder = directory.getAbsolutePath();

					File[] tempExerciseFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".sql"));

					for (File ef : tempExerciseFiles) {
						// get just the filenames so that we don't have to hard code the exercise file names and can find just by number and can find just by number
						exerFiles.add(ef.getName());
					}

					break;
				}
				else {
					if (currPath.equals("C:") || currPath.equals("/")) //ran out of locations to check
					{
						break;
					}

					//go up one level
					currPath = currPath.substring(0, currPath.lastIndexOf("/"));
				}
			}
		}

		return exerFiles;
	}

	private static SingleConnectionDataSource createDatasource(String defaultDbName) {
		String host = System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";
		String port = System.getenv("DB_PORT") != null ? System.getenv("DB_PORT") : "5432";
		String dbName = System.getenv("DB_DATABASE") != null ? System.getenv("DB_DATABASE") : defaultDbName;
		String username = System.getenv("DB_USERNAME") != null ? System.getenv("DB_USERNAME") : "postgres";
		String password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "postgres1";

		String url = String.format("jdbc:postgresql://%s:%s/%s", host, port, dbName);

		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}
}
