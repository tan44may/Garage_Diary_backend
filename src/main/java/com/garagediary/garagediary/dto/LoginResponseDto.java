package com.garagediary.garagediary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String token;
    private String role;
}
