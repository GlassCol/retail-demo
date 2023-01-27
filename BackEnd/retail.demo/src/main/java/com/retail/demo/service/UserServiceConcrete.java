package com.retail.demo.service;

import com.retail.demo.dao.UserDao;
import com.retail.demo.entity.UserEntity;
import com.retail.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceConcrete implements UserService {
    private final UserDao userDao;

    private User currentUser = null;

    public UserServiceConcrete(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean isDaoEmpty() {
        return userDao.findAll().isEmpty();
    }

    @Override
    public User createUser(User user) {
        if(getUserByUsername(user.getUsername()) != null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userDao.save(userEntity);
        return user;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        if(getUserByUsername(username) == null) {
            return false;
        }
        return getUserByUsername(username).getPassword().equals(password);
    }

    @Override
    public User getUserByUsername(String username) {
        List<UserEntity> userList = userDao.findAll();
        for (UserEntity userEntity : userList) {
            if (userEntity.getUsername().contains(username)) {
                User user = new User();
                BeanUtils.copyProperties(userEntity, user);
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        try{
            UserEntity userEntity = userDao.findById(id).get();
            userDao.delete(userEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public User setCurrentUser(User user) {
        this.currentUser = user;
        return this.currentUser;
    }

    @Override
    public boolean resetCurrentUser() {
        this.currentUser = null;
        return true;
    }


}
