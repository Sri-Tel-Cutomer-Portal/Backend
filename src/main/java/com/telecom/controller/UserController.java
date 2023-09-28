package com.telecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.telecom.model.User;
import com.telecom.repository.UserRepository;
import com.telecom.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository repo;

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @GetMapping("/id")
    public Long findUserId(User user) {
        Long id = user.getId();
        return id;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User userData) {
        User user = repo.findByuserName(userData.getUserName());
        System.out.println(userData.getUserName());
        if (user.getPassword().equals(userData.getPassword())) {
            return ResponseEntity.ok(user.getId());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect username and password combination");

    }

}
