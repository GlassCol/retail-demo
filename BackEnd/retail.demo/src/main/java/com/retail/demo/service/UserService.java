package com.retail.demo.service;

import com.retail.demo.model.User;

public interface UserService {

    boolean isDaoEmpty();

    User createUser(User user);

    boolean checkLogin(String username, String password);

    User getUserByUsername(String username);

    boolean deleteUser(Long id);

    User getCurrentUser();

    User setCurrentUser(User user);

    boolean resetCurrentUser();

    User getLastUser();
}
