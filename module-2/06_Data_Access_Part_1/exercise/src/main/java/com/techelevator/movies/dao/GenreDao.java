package com.techelevator.movies.dao;

import com.techelevator.movies.model.Genre;

import java.util.List;

public interface GenreDao {

    /**
     * Get a list of all genres from the datastore.
     * The list is never null. It is empty if there are no genres in the datastore.
     *
     * @return all genres as a list of Genre objects
     */
    List<Genre> getGenres();

    /**
     * Get a genre from the datastore that has the given id.
     * If the id is not found, return null.
     *
     * @param id the genre id to get from the datastore
     * @return a fully populated Genre object
     */
    Genre getGenreById(int id);

    /**
     * Get a list of all the genres from the datastore that match the name.
     * The list is never null. It is empty if no matching genres were found.
     *
     * @param name the genre name to get from the datastore
     * @param useWildCard wraps name with wild card characters if true
     * @return all matching genres as a list of Genre objects
     */
    List<Genre> getGenresByName(String name, boolean useWildCard);
}
