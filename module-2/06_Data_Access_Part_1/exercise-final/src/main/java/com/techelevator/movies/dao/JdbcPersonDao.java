package com.techelevator.movies.dao;

import com.techelevator.movies.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcPersonDao implements PersonDao {

    private final String PERSON_SELECT = "SELECT p.person_id, p.person_name, p.birthday, " +
        "p.deathday, p.biography, p.profile_path, p.home_page FROM person p ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcPersonDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Person> getPersons() {

        List<Person> persons = new ArrayList<>();
        String sqlGetPersons = PERSON_SELECT;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPersons);
        while (results.next()) {
            persons.add(mapRowToPerson(results));
        }
        return persons;
    }

    @Override
    public Person getPersonById(int id) {

        Person person = null;
        String sqlGetMovie = PERSON_SELECT + " WHERE p.person_id=?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMovie, id);
        if (results.next()) {
            person = mapRowToPerson(results);
        }
        return person;
    }

    @Override
    public List<Person> getPersonsByName(String name, boolean useWildCard) {

        List<Person> persons = new ArrayList<>();
        if (useWildCard) {
            name = "%" + name + "%";
        }
        String sqlGetPersons = PERSON_SELECT +  " WHERE p.person_name ILIKE ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPersons, name);
        while (results.next()) {
            persons.add(mapRowToPerson(results));
        }
        return persons;
    }

    @Override
    public List<Person> getPersonsByCollectionName(String collectionName, boolean useWildCard) {

        List<Person> persons = new ArrayList<>();
        if (useWildCard) {
            collectionName = "%" + collectionName + "%";
        }
        String sqlGetMovies = "SELECT DISTINCT p.person_id, p.person_name, " +
                "p.birthday, p.deathday, p.biography, p.profile_path, p.home_page " +
                "FROM person p " +
                "JOIN movie_actor ma ON p.person_id = ma.actor_id " +
                "JOIN movie m ON ma.movie_id = m.movie_id " +
                "JOIN collection c ON m.collection_id = c.collection_id " +
                "WHERE c.collection_name ILIKE ? " +
                "ORDER BY p.person_name";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMovies, collectionName);
        while (results.next()) {
            persons.add(mapRowToPerson(results));
        }
        return persons;
    }

    private Person mapRowToPerson(SqlRowSet sqlRowSet) {

        Person person = new Person();
        person.setId(sqlRowSet.getInt("person_id"));
        person.setName(sqlRowSet.getString("person_name"));
        if (sqlRowSet.getDate("birthday") != null) {
            person.setBirthday(sqlRowSet.getDate("birthday").toLocalDate());
        }
        if (sqlRowSet.getDate("deathday") != null) {
            person.setDeathDate(sqlRowSet.getDate("deathday").toLocalDate());
        }
        person.setBiography(sqlRowSet.getString("biography"));
        person.setProfilePath(sqlRowSet.getString("profile_path"));
        person.setHomePage(sqlRowSet.getString("home_page"));
        return person;
    }
}
