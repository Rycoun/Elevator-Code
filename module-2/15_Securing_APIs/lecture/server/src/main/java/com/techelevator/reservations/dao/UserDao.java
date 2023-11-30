package com.techelevator.reservations.dao;

import com.techelevator.reservations.model.User;

public interface UserDao {

    User getUserByUsername(String username);

}
