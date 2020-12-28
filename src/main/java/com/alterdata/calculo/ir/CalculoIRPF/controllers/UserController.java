package com.alterdata.calculo.ir.CalculoIRPF.controllers;

import com.alterdata.calculo.ir.CalculoIRPF.entity.User;
import com.alterdata.calculo.ir.CalculoIRPF.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    @ResponseStatus (HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {

        return userRepository.save(user);
    }



}
