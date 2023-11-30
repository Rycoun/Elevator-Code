package com.techelevator.movies.dao;

import com.techelevator.movies.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class JdbcCollectionDao implements CollectionDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Collection> getCollections() {
        String sql = "SELECT collection_id, collection_name FROM collection";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        List<Collection> collections = new ArrayList<>();
        while (rowSet.next()) {
            collections.add(new Collection(rowSet.getInt("collection_id"), rowSet.getString("collection_name")));
        }
        return collections;

    }

    @Override
    public Collection getCollectionById(int id) {
        String sql = "SELECT collection_id, collection_name FROM collection WHERE collection_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        if (rowSet.next()) {
            return new Collection(rowSet.getInt("collection_id"), rowSet.getString("collection_name"));
        } else {
            return null;
        }
    }

    @Override
    public List<Collection> getCollectionsByName(String name, boolean useWildCard) {
        List<Collection> collections = new ArrayList<>();
        String sql = "select collection_id, collection_name from collection where collection_name ilike ? order by collection_name asc ";

        if (useWildCard) {
            name = "%" + name + "%";

        }
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, name);

        while (rowSet.next()) {
            Collection collection = mapRowToCollection(rowSet);

            collections.add(collection);

        }
        return collections;
    }

    private Collection mapRowToCollection(SqlRowSet rowSet) {
        Collection collection = new Collection();

        collection.setId(rowSet.getInt("collection_id"));
        collection.setName(rowSet.getString("collection_name"));

        return collection;

    }
}
