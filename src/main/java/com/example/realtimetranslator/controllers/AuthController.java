package com.example.realtimetranslator.controllers;

import com.example.realtimetranslator.dtos.LoginRequestDto;
import com.example.realtimetranslator.dtos.LoginResponseDto;
import com.example.realtimetranslator.dtos.RegisterRequestDto;
import com.example.realtimetranslator.dtos.RegisterResponseDto;
import com.example.realtimetranslator.enums.ResponseStatus;
import com.example.realtimetranslator.models.User;
import com.example.realtimetranslator.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
     public AuthController(AuthService authService){
         this.authService = authService;
     }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        User user = authService.login(requestDto);
        LoginResponseDto responseDto = new LoginResponseDto();

        if (user != null) {
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMsg("User logged in successfully");
            responseDto.setUser(user);
            return ResponseEntity.ok(responseDto);
        }else{
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMsg("User login failed");
            return ResponseEntity.badRequest().body(responseDto);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto requestDto) {
        boolean status =  authService.register(requestDto);
        RegisterResponseDto responseDto = new RegisterResponseDto();

        if (status) {
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMsg("User registered successfully");
            return ResponseEntity.created(null).body(responseDto);
        }else{
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMsg("User registration failed");
            return ResponseEntity.badRequest().body(responseDto);
        }

    }
}
