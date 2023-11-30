package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Park;

import java.util.List;

public interface ParkDao {

    /**
     * Get a park from the datastore given the park id.
     * If the given park id is not found, return null.
     *
     * @param parkId The id of the park to get from the datastore.
     * @return a fully populated Park object.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    Park getParkById(int parkId);

    /**
     * Get a list of parks (unordered) in a state from the datastore given the state abbreviation.
     * The list is never null. It is empty if there are no parks for the given state abbreviation
     *      in the datastore.
     *
     * @param stateAbbreviation The state abbreviation of the parks to get from the datastore
     * @return a list of Park objects.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    List<Park> getParksByState(String stateAbbreviation);

    /**
     * Add a new park to the datastore based upon the given Park object.
     * The given Park object does not need to be fully populated, only the properties required by
     *      the target datastore.
     *
     * @param park The Park object to add to the datastore.
     * @return a fully populated Park object.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    Park createPark(Park park);

    /**
     * Update an existing park in the datastore with the property values of the given Park object.
     * The given Park object needs to be fully populated, not just the properties to be updated.
     *
     * @param park The Park object to update in the datastore.
     * @return a fully populated updated Park object.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions. Also thrown if method updates zero records.
     */
    Park updatePark(Park park);

    /**
     * Remove an existing park from the datastore given a park id.
     * If the park id is not found, return 0.
     *
     * @param parkId The id of the park to remove from the datastore.
     * @return the number of parks removed from the datastore.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    int deleteParkById(int parkId);

    /**
     * Create a relationship/association between the park identified by parkId and
     *      the state identified by stateAbbreviation in the datastore.
     *
     * @param parkId The id of the park to relate with the state.
     * @param stateAbbreviation The state abbreviation of the state to relate with the park.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    void linkParkState(int parkId, String stateAbbreviation);

    /**
     * Remove the relationship/association between the park identified by parkId and
     *      the state identified by stateAbbreviation in the datastore.
     *
     * @param parkId The id of the park to disassociate from the state.
     * @param stateAbbreviation The state abbreviation of the state to disassociate from the park.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    void unlinkParkState(int parkId, String stateAbbreviation);
}
