package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.State;

import java.util.List;

public interface StateDao {

    /**
     * Get a state from the datastore given the state abbreviation.
     * If the given state abbreviation is not found, return null.
     *
     * @param stateAbbreviation The state abbreviation of the state to get from the datastore.
     * @return a fully populated State object.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    State getStateByAbbreviation(String stateAbbreviation);

    /**
     * Get a state from the datastore given the capital city id.
     * If the given capital city id is not found, return null.
     *
     * @param cityId The id of the capital city of the state to get from the datastore.
     * @return a fully populated State object.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    State getStateByCapital(int cityId);

    /**
     * Get a list of all the states (unordered) in the datastore.
     * The list is never null. It is empty if there are no states in the datastore.
     *
     * @return all the states as a list of State objects.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    List<State> getStates();
}
