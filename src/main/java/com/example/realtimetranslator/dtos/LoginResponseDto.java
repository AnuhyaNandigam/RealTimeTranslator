package com.example.realtimetranslator.dtos;

import com.example.realtimetranslator.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private ResponseStatus responseStatus;
    private String msg;
}
