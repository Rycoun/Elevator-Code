package com.techelevator.movies.dao;

import com.techelevator.movies.model.Person;

import java.util.List;

public interface PersonDao {

    /**
     * Get a list of all persons from the datastore.
     * The list is never null. It is empty if there are no persons in the datastore.
     *
     * @return all persons as a list of Person objects
     */
    List<Person> getPersons();

    /**
     * Get a person from the datastore that has the given id.
     * If the id is not found, return null.
     *
     * @param id the person id to get from the datastore
     * @return a fully populated Person object
     */
    Person getPersonById(int id);

    /**
     * Get a list of all the persons from the datastore that match the name.
     * The list is never null. It is empty if no matching persons were found.
     *
     * @param name the person name to get from the datastore
     * @param useWildCard wraps name with wild card characters if true
     * @return all matching persons as a list of Person objects
     */
    List<Person> getPersonsByName(String name, boolean useWildCard);

    /**
     * Get all the actors in the movies that make up a given collection. The list
     * is ordered by the actors' names. This list is never null. It is empty if
     * no matching actors were found.
     *
     * The actors must appear only once in the list. Hint: Use DISTINCT
     *
     * @param collectionName the name of the given collection
     * @param useWildCard wraps collectionName with wild card characters if true
     * @return all matching persons (actors) as a list of Person objects
     */
    List<Person> getPersonsByCollectionName(String collectionName, boolean useWildCard);
}
