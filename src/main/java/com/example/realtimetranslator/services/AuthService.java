package com.example.realtimetranslator.services;

import com.example.realtimetranslator.dtos.LoginRequestDto;
import com.example.realtimetranslator.dtos.RegisterRequestDto;
import com.example.realtimetranslator.models.User;
import com.example.realtimetranslator.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(RegisterRequestDto requestDto) {
        Optional<User> userOptional = userRepository.getByEmail(requestDto.getEmail());

        if(userOptional.isPresent()){
            return false;
        }

        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());

        userRepository.save(user);

        return true;
    }

    public boolean login(LoginRequestDto requestDto) {
        Optional<User> userOptional = userRepository.getByEmail(requestDto.getEmail());

        if(userOptional.isEmpty()){
            return false;
        }

        User user = userOptional.get();
        return user.getPassword().equals(requestDto.getPassword());
    }
}
