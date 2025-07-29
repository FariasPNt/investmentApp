package com.antoniofarias.investmentapp.controller;

import com.antoniofarias.investmentapp.dto.CreateUserDto;
import com.antoniofarias.investmentapp.entity.User;
import com.antoniofarias.investmentapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto userDto){
        var userId = userService.createUser(userDto);
        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){

        return null;
    }
}
