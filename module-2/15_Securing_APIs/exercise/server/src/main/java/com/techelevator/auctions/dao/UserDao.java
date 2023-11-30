package com.techelevator.auctions.dao;

import com.techelevator.auctions.model.User;

public interface UserDao {

    User getUserByUsername(String username);

}
