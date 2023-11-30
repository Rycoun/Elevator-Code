package com.techelevator.movies.dao;

import com.techelevator.movies.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCollectionDao implements CollectionDao{

    private final String COLLECTION_SELECT = "SELECT c.collection_id, c.collection_name FROM collection c ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Collection> getCollections() {

        List<Collection> collections = new ArrayList<>();
        String sqlGetCollections = COLLECTION_SELECT;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCollections);
        while (results.next()) {
            collections.add(mapRowToCollection(results));
        }
        return collections;
    }

    @Override
    public Collection getCollectionById(int id) {

        Collection collection = null;
        String sqlGetCollection = COLLECTION_SELECT + " WHERE c.collection_id=?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCollection, id);
        if (results.next()) {
            collection = mapRowToCollection(results);
        }
        return collection;
    }

    @Override
    public List<Collection> getCollectionsByName(String name, boolean useWildCard) {

        List<Collection> collections = new ArrayList<>();
        if (useWildCard) {
            name = "%" + name + "%";
        }
        String sqlGetCollections = COLLECTION_SELECT + " WHERE c.collection_name ILIKE ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCollections, name);
        while (results.next()) {
            collections.add(mapRowToCollection(results));
        }
        return collections;
    }

    private Collection mapRowToCollection(SqlRowSet sqlRowSet) {

        Collection collection = new Collection();
        collection.setId(sqlRowSet.getInt("collection_id"));
        collection.setName(sqlRowSet.getString("collection_name"));
        return collection;
    }
}
