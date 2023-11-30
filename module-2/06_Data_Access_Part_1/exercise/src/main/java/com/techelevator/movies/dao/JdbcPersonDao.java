package com.techelevator.movies.dao;

import com.techelevator.movies.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcPersonDao implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPersonDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Person> getPersons() {
        String sql = "select * from person";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        List<Person> people = new ArrayList<>();

        while (result.next()) {
            Person person = new Person();
            person.setId(result.getInt("person_id"));
            person.setName(result.getString("person_name"));

            people.add(person);
        }
        return people;
    }

    @Override
    public Person getPersonById(int id) {
        String sql = "select * from person where person_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        if (result.next()) {
            Person person = new Person();
            person.setId(result.getInt("person_id"));
            person.setName(result.getString("person_name"));

            return person;
        } else {
            return null;
        }
    }
    @Override
    public List<Person> getPersonsByName(String name, boolean useWildCard) {
        String sql = "SELECT * FROM person WHERE person_name";
        if (useWildCard) {
            sql += " ILIKE ?";
            name = "%" + name + "%";
        } else {
            sql += " = ?";

        }
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
        List<Person> persons = new ArrayList<>();
        while (results.next()) {
            Person person = new Person();
            person.setId(results.getInt("person_id"));
            person.setName(results.getString("person_name")); // Set other person properties as needed persons.add(person); } return persons; }
            persons.add(person);

        }
        return persons;
    }

    @Override
    public List<Person> getPersonsByCollectionName(String collectionName, boolean useWildCard) {
        String sql = " select distinct person.* from person join movie_actor on actor_id = person_id join movie using (movie_id) join collection using (collection_id) where collection_name ilike ? order by person_name asc;";


        if (useWildCard) {
            collectionName = "%" + collectionName + "%";
        }
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionName);
        List<Person> people = new ArrayList<>();

        while (results.next()) {
            Person person = new Person();
            person.setId(results.getInt("person_id"));
            person.setName(results.getString("person_name"));

            people.add(person);
        }
        return people;

    }
}
