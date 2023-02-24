package com.docapp.springjwt.controllers;

import com.docapp.springjwt.models.User;
import com.docapp.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();
        HashMap<String, Object> response = new HashMap<>();
        response.put("users", users);
        return ResponseEntity.ok(response);

    }


}
