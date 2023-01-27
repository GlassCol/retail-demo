package com.retail.demo.controller;


import com.retail.demo.dao.UserDao;
import com.retail.demo.model.User;
import com.retail.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userApi/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private UserDao inventoryItemDao;
    @Autowired
    private UserService userService;

    @GetMapping("/user/{userName}")
    public User getUser(@PathVariable String userName){
        return userService.getUserByUsername(userName);
    }

    @GetMapping("/user/{username}/{password}")
    public boolean checkLogin(@PathVariable String username, @PathVariable String password){
        return userService.checkLogin(username, password);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/user/currentUser")
    public User setCurrentUser(@RequestBody User user){
        return userService.setCurrentUser(user);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long userId){
        boolean deleted = false;
        deleted = userService.deleteUser(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/currentUser")
    public User getCurrentUser(){
        return userService.getCurrentUser();
    }

    @PostMapping("/user/reset")
        public boolean resetCurrentUser(){
            return userService.resetCurrentUser();
        }

}
