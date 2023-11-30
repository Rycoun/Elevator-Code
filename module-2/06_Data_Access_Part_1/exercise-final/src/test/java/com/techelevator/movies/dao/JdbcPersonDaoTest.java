package com.techelevator.movies.dao;

import com.techelevator.movies.model.Person;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcPersonDaoTest extends BaseDaoTest {

    private static final Person PERSON_1 =
            new Person(1,
                    "George Lucas",
                    LocalDate.of(1944, 5, 14),
                    null,
                    null,
                    "https://image.tmdb.org/t/p/w185/WCSZzWdtPmdRxH9LUCVi2JPCSJ.jpg",
                    null
            );

    private JdbcPersonDao sut;

    @Before
    public void setup() {
        sut = new JdbcPersonDao(dataSource);
    }

    @Test
    public void getPersons_returns_correct_number_of_persons() {

        List<Person> persons = sut.getPersons();
        assertNotNull("getPersons returned a null list of persons.", persons);
        assertEquals("getPersons returned the wrong number of persons in the list.",
                331, persons.size());
    }

    @Test
    public void getPersonById_with_valid_id_returns_correct_person() {

        Person person = sut.getPersonById(1);
        assertNotNull("getPersonById with valid id returned a null person.", person);
        assertPersonsMatch("getPersonById with valid id returned the incorrect/incomplete person.", PERSON_1, person);
    }

    @Test
    public void getPersonById_with_invalid_id_returns_null_person() {

        Person person = sut.getPersonById(0); // IDs begin with 1, cannot be 0
        assertNull("getPersonById with invalid id returned a person rather than null.", person);
    }

    @Test
    public void getPersonsByName_with_full_name_exact_match_returns_correct_number_of_persons() {

        List<Person> persons = sut.getPersonsByName("George Lucas", false);
        assertNotNull("getPersonsByName with full name exact match returned a null list of persons.", persons);
        assertEquals("getPersonsByName with full name exact match returned the wrong number of persons in the list.",
                1, persons.size());
    }

    @Test
    public void getPersonsByName_with_partial_name_exact_match_returns_correct_number_of_persons() {

        List<Person> persons = sut.getPersonsByName("rge Luc", false);
        assertNotNull("getPersonsByName with partial name exact match returned a null list of persons.", persons);
        assertEquals("getPersonsByName with partial name exact match returned the wrong number of persons in the list.",
                0, persons.size());
    }

    @Test
    public void getPersonsByName_with_empty_name_exact_match_returns_correct_number_of_persons() {

        List<Person> persons = sut.getPersonsByName("", false);
        assertNotNull("getPersonsByName with empty name exact match returned a null list of persons.", persons);
        assertEquals("getPersonsByName with empty name exact match returned the wrong number of persons in the list.",
                0, persons.size());
    }

    @Test
    public void getPersonsByName_with_partial_name_wildcard_match_returns_correct_number_of_persons() {

        List<Person> persons = sut.getPersonsByName("rge Luc", true);
        assertNotNull("getPersonsByName with partial name wildcard match returned a null list of persons.", persons);
        assertEquals("getPersonsByName with partial name wildcard match returned the wrong number of persons in the list.",
                1, persons.size());
    }

    @Test
    public void getPersonsByName_with_empty_name_wildcard_match_returns_correct_number_of_persons() {

        List<Person> persons = sut.getPersonsByName("", true);
        assertNotNull("getPersonsByName with empty name wildcard match returned a null list of persons.", persons);
        assertEquals("getPersonsByName with empty name wildcard match returned the wrong number of persons in the list.",
                331, persons.size());
    }

    @Test
    public void getPersonsByCollectionName_with_full_collection_name_exact_match_returns_correct_number_of_persons() {

        List<Person> persons = sut.getPersonsByCollectionName("Star Wars Collection", false);
        assertNotNull("getPersonsByCollectionName with full collection name exact match returned a null list of persons.", persons);
        assertEquals("getPersonsByCollectionName with full collection name exact match returned the wrong number of persons in the list.",
                25, persons.size());
    }

    @Test
    public void getPersonsByCollectionName_with_partial_collection_name_exact_match_returns_correct_number_of_persons() {

        List<Person> persons = sut.getPersonsByCollectionName("Star", false);
        assertNotNull("getPersonsByCollectionName with partial name exact match returned a null list of persons.", persons);
        assertEquals("getPersonsByCollectionName with partial name exact match returned the wrong number of persons in the list.",
                0, persons.size());
    }

    @Test
    public void getPersonsByCollectionName_with_partial_collection_name_wildcard_match_returns_correct_number_of_persons() {

        List<Person> persons = sut.getPersonsByCollectionName("F", true);
        assertNotNull("getPersonsByCollectionName with partial name wildcard match returned a null list of persons.", persons);
        assertEquals("getPersonsByCollectionName with partial name wildcard match returned the wrong number of persons in the list.",
                29, persons.size());
    }

    private void assertPersonsMatch(String message, Person expected, Person actual) {

        assertEquals(message, expected.getId(), actual.getId());
        assertEquals(message, expected.getName(), actual.getName());
        assertEquals(message, expected.getBirthday(), actual.getBirthday());
        assertEquals(message, expected.getDeathDate(), actual.getDeathDate());
        assertEquals(message, expected.getBiography(), actual.getBiography());
        assertEquals(message, expected.getProfilePath(), actual.getProfilePath());
        assertEquals(message, expected.getHomePage(), actual.getHomePage());
    }
}
