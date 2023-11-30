package com.techelevator.guessnumber.dao;

import com.techelevator.guessnumber.model.RegisterUserDto;
import com.techelevator.guessnumber.model.User;

public interface UserDao {

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
