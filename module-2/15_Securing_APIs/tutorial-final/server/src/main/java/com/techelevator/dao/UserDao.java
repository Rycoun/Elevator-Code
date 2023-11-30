package com.techelevator.dao;

import com.techelevator.model.User;

public interface UserDao {

    User getUserByUsername(String username);

}
