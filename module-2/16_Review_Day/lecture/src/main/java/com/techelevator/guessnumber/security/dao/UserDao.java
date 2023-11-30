package com.techelevator.guessnumber.security.dao;

import com.techelevator.guessnumber.security.model.RegisterUserDto;
import com.techelevator.guessnumber.security.model.User;

public interface UserDao {

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
