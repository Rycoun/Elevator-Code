package com.techelevator.dao;

import com.techelevator.model.City;

import java.util.List;

public interface CityDao {

    /**
     * Get the count of all the cities in the datastore.
     * If there are no cities in the datastore, return 0.
     *
     * @return the number of cities in the datastore.
     */
    int getCityCount();

    /**
     * Get the population of the largest city in the datastore.
     * If there are no cities in the datastore, return 0.
     *
     * @return the population of the largest city.
     */
    int getMostPopulatedCity();

    /**
     * Get the population of the smallest city in the datastore.
     * If there are no cities in the datastore, return 0.
     *
     * @return the population of the smallest city.
     */
    int getLeastPopulatedCity();

    /**
     * Get the average area of all the cities in the datastore.
     * If there are no cities in the datastore, return 0.
     *
     * @return the average area of all the cities.
     */
    double getAverageCityArea();

    /**
     * Get a list of all the city names (ascending order) in the datastore.
     * The list is never null. It is empty if there are no city names in the datastore.
     *
     * @return all the city names as a list of Strings.
     */
    List<String> getCityNames();

    /**
     * Get a randomly selected city from the datastore.
     * If there are no cities in the datastore, return null.
     *
     * @return the fully populated City object randomly selected.
     */
    City getRandomCity();

    /**
     * Get a city from the datastore given the city id.
     * If the given city id is not found, return null.
     *
     * @param cityId The id of the city to get from the datastore.
     * @return a fully populated City object.
     */
    City getCityById(int cityId);

    /**
     * Get a list of cities (unordered) in a state from the datastore given the state abbreviation.
     * The list is never null. It is empty if there are no cities for the given state abbreviation
     *      in the datastore.
     *
     * @param stateAbbreviation The state abbreviation of the cities to get from the datastore.
     * @return a list of City objects.
     */
    List<City> getCitiesByState(String stateAbbreviation);
}
