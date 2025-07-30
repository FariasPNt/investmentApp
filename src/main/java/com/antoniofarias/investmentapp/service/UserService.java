package com.antoniofarias.investmentapp.service;

import com.antoniofarias.investmentapp.dto.CreateUserDto;
import com.antoniofarias.investmentapp.dto.UpdateUserDto;
import com.antoniofarias.investmentapp.entity.User;
import com.antoniofarias.investmentapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {
        var entity = new User(createUserDto.username(),
                createUserDto.email(),
                createUserDto.password());

        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }

    public Optional<User> getById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDto updateUserDto) {
        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            var user = userEntity.get();
            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }
            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }
            userRepository.save(user);
        }
    }

    public void deleteById(String userId) {
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);
        if (userExists) {
            userRepository.deleteById(id);
        }
    }
}
