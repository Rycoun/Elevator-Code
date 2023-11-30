package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.City;

import java.util.List;

public interface CityDao {

    /**
     * Get a city from the datastore given the city id.
     * If the given city id is not found, return null.
     *
     * @param cityId The id of the city to get from the datastore.
     * @return a fully populated City object.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    City getCityById(int cityId);

    /**
     * Get a list of cities (unordered) in a state from the datastore given the state abbreviation.
     * The list is never null. It is empty if there are no cities for the given state abbreviation
     *      in the datastore.
     *
     * @param stateAbbreviation The state abbreviation of the cities to get from the datastore
     * @return a list of City objects.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    List<City> getCitiesByState(String stateAbbreviation);

    /**
     * Add a new city to the datastore based upon the given City object.
     * The given City object does not need to be fully populated, only the properties required by
     *      the target datastore.
     *
     * @param city The City object to add to the datastore.
     * @return a fully populated City object.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    City createCity(City city);

    /**
     * Update an existing city in the datastore with the property values of the given City object.
     * The given City object needs to be fully populated, not just the properties to be updated.
     *
     * @param city The City object to update in the datastore.
     * @return a fully populated updated City object.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions. Also thrown if method updates zero records.
     */
    City updateCity(City city);

    /**
     * Remove an existing city from the datastore given a city id.
     * If the city id is not found, return 0.
     *
     * @param cityId The id of the city to remove from the datastore.
     * @return the number of cities removed from the datastore.
     * @throws DaoException if an error occurs such as failure to connect with the datastore
     *      or other datastore-specific exceptions.
     */
    int deleteCityById(int cityId);
}
