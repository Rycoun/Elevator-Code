package com.techelevator.dao.users;

import com.techelevator.model.users.User;

public interface UserDAO {

    User findByUsername(String username);

    boolean create(String username, String password, String role);
}
