package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDto {
    private UUID user_id;
    private String email;
    private String name;
    private String phone;
    private Role role;
    private String token;
}
