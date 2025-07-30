package com.antoniofarias.investmentapp.controller;

import com.antoniofarias.investmentapp.dto.CreateUserDto;
import com.antoniofarias.investmentapp.dto.UpdateUserDto;
import com.antoniofarias.investmentapp.entity.User;
import com.antoniofarias.investmentapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
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
        var user = userService.getById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        var users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") String userId,
                                           @RequestBody UpdateUserDto updateUserDto){
        userService.updateUserById(userId, updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId){
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
