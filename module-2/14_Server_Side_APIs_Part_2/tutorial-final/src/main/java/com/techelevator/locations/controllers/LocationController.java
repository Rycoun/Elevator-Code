package com.techelevator.locations.controllers;

import com.techelevator.locations.dao.LocationDao;
import com.techelevator.locations.exception.DaoException;
import com.techelevator.locations.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationDao locationDao;

    public LocationController(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Location> list() {
        return locationDao.getLocations();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Location get(@PathVariable int id) {
        Location location = locationDao.getLocationById(id);
        if (location == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        } else {
            return location;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Location add(@Valid @RequestBody Location location) {
        return locationDao.createLocation(location);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Location update(@Valid @RequestBody Location location, @PathVariable int id) {
        // The id on the path takes precedence over the id in the request body, if any
        location.setId(id);
        try {
            Location updatedLocation = locationDao.updateLocation(location);
            return updatedLocation;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        locationDao.deleteLocationById(id);
    }

}
