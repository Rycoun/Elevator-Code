package com.techelevator.dao;

import com.techelevator.model.Location;

import java.util.List;

public interface LocationDao {

    List<Location> getLocations();

    Location getLocationById(int id);

    Location createLocation(Location location);

    Location updateLocation(Location location);

    int deleteLocationById(int id);

}
