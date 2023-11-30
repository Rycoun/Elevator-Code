package com.techelevator.controller;

import com.techelevator.dao.LocationDao;
import com.techelevator.dao.MemoryLocationDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/locations")
@PreAuthorize("isAuthenticated()")
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
    public Location add(@Valid @RequestBody Location location, Principal principal) {
        System.out.println(principal.getName());
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
