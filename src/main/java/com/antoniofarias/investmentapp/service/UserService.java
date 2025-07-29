package com.antoniofarias.investmentapp.service;

import com.antoniofarias.investmentapp.dto.CreateUserDto;
import com.antoniofarias.investmentapp.entity.User;
import com.antoniofarias.investmentapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto){
        var entity = new User(createUserDto.username(),
                createUserDto.email(),
                createUserDto.password());

        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }
}
