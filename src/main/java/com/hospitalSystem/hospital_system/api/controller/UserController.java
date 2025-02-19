package com.hospitalSystem.hospital_system.api.controller;

import com.hospitalSystem.hospital_system.api.model.User;
import com.hospitalSystem.hospital_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/adduser")
    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user){
       User response = userRepository.save(user);
       return ResponseEntity.ok(response);
    }

    @RequestMapping("/viewUsers")
    @GetMapping()
    public List<User> getAllUsers(){
        List<User> usersList = userRepository.findAll();
        return usersList;
    }

    @RequestMapping("/getUserById/{id}")
    @GetMapping()
    public User getUserById(@PathVariable Long id){
        User userById = userRepository.findById(id).orElse(null);
        return userById;
    }
}
