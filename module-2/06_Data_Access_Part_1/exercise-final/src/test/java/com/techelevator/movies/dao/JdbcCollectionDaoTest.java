package com.techelevator.movies.dao;

import com.techelevator.movies.model.Collection;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcCollectionDaoTest extends BaseDaoTest {

    private final Collection COLLECTION_86311 =
            new Collection(86311, "The Avengers Collection");

    private JdbcCollectionDao sut;

    @Before
    public void setup() {
        sut = new JdbcCollectionDao(dataSource);
    }

    @Test
    public void getCollections_returns_correct_number_of_collections() {

        List<Collection> collections = sut.getCollections();
        assertNotNull("getCollections returned a null list of collections.", collections);
        assertEquals("getCollections returned the wrong number of collections in the list.",
                6, collections.size());
    }

    @Test
    public void getCollectionById_with_valid_id_returns_correct_collection() {

        Collection collection = sut.getCollectionById(86311);
        assertNotNull("getCollectionById with valid id returned a null collection.", collection);
        assertCollectionsMatch("getCollectionById with valid id returned the incorrect/incomplete collection.", COLLECTION_86311, collection);
    }

    @Test
    public void getCollectionById_with_invalid_id_returns_null_collection() {

        Collection collection = sut.getCollectionById(0); // IDs begin with 1, cannot be 0
        assertNull("getCollectionById with invalid id returned a collection rather than null.", collection);
    }

    @Test
    public void getCollectionsByName_with_full_name_exact_match_returns_correct_number_of_collections() {

        List<Collection> collections = sut.getCollectionsByName("the avengers collection", false);
        assertNotNull("getCollectionsByName with full name exact match returned a null list of collections.", collections);
        assertEquals("getCollectionsByName with full name exact match returned the wrong number of collections in the list.",
                1, collections.size());
    }

    @Test
    public void getCollectionsByName_with_partial_name_exact_match_returns_correct_number_of_collections() {

        List<Collection> collections = sut.getCollectionsByName("e avengers c", false);
        assertNotNull("getCollectionsByName with partial name exact match returned a null list of collections.", collections);
        assertEquals("getCollectionsByName with partial name exact match returned the wrong number of collections in the list.",
                0, collections.size());
    }

    @Test
    public void getCollectionsByName_with_empty_name_exact_match_returns_correct_collections() {

        List<Collection> collections = sut.getCollectionsByName("", false);
        assertNotNull("getCollectionsByName with empty name exact match returned a null list of collections.", collections);
        assertEquals("getCollectionsByName with empty name exact match returned the wrong number of collections in the list.",
                0, collections.size());
    }

    @Test
    public void getCollectionsByName_with_partial_name_wildcard_match_returns_correct_number_of_collections() {

        List<Collection> collections = sut.getCollectionsByName("e avengers c", true);
        assertNotNull("getCollectionsByName with partial name wildcard match returned a null list of collections.", collections);
        assertEquals("getCollectionsByName with partial name wildcard match returned the wrong number of collections in the list.",
                1, collections.size());
    }

    @Test
    public void getCollectionsByName_with_empty_name_wildcard_match_returns_correct_number_of_collections() {

        List<Collection> collections = sut.getCollectionsByName("", true);
        assertNotNull("getCollectionsByName with empty name wildcard match returned a null list of collections.", collections);
        assertEquals("getCollectionsByName with empty name wildcard match returned the wrong number of collections in the list.",
                6, collections.size());
    }

    private void assertCollectionsMatch(String message, Collection expected, Collection actual) {

        assertEquals(message, expected.getId(), actual.getId());
        assertEquals(message, expected.getName(), actual.getName());
    }
}
