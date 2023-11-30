package com.techelevator.dao;

import com.techelevator.model.Park;

import java.time.LocalDate;
import java.util.List;

public interface ParkDao {

    /**
     * Get the count of all the parks in the datastore.
     * If there are no parks in the datastore, return 0.
     *
     * @return the number of parks in the datastore.
     */
    int getParkCount();

    /**
     * Get the date established of the oldest park in the datastore.
     * If there are no parks in the datastore, return null.
     *
     * @return the date established of the oldest park.
     */
    LocalDate getOldestParkDate();

    /**
     * Get the average area of all the parks in the datastore.
     * If there are no parks in the datastore, return 0.
     *
     * @return the average area of all the parks.
     */
    double getAverageParkArea();

    /**
     * Get a list of all the park names (ascending order) in the datastore.
     * The list is never null. It is empty if there are no park names in the datastore.
     *
     * @return all the park names as a list of Strings.
     */
    List<String> getParkNames();

    /**
     * Get a randomly selected park from the datastore.
     * If there are no parks in the datastore, return null.
     *
     * @return the fully populated Park object randomly selected.
     */
    Park getRandomPark();

    /**
     * Get a list of all the parks with camping from the datastore.
     * The list is never null. It is empty if there are no parks with camping in the datastore.
     *
     * @return all parks with camping as a list of Park objects.
     */
    List<Park> getParksWithCamping();

    /**
     * Get a park from the datastore given the park id.
     * If the given park id is not found, return null.
     *
     * @param parkId The id of the park to get from the datastore.
     * @return a fully populated Park object.
     */
    Park getParkById(int parkId);

    /**
     * Get a list of parks (unordered) in a state from the datastore given the state abbreviation.
     * The list is never null. It is empty if there are no parks for the given state abbreviation
     *      in the datastore.
     *
     * @param stateAbbreviation The state abbreviation of the parks to get from the datastore.
     * @return a list of Park objects.
     */
    List<Park> getParksByState(String stateAbbreviation);

    /**
     * Get a list of park(s) (unordered) from the datastore given the park name.
     * The search by park name is always case-insensitive regardless of useWildCard parameter.
     * The list is never null. It is empty if there are no parks for the given park name in the datastore.
     *
     * @param name The name of the park(s) to get from the datastore.
     * @param useWildCard If true the park name is wrapped with wildcard characters, otherwise no wildcards
     *      are applied. The search is always case-insensitive.
     * @return a list of Park objects.
     */
    List<Park> getParksByName(String name, boolean useWildCard);
}
