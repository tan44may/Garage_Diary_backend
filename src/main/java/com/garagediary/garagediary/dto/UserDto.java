package com.garagediary.garagediary.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDto {
    private UUID user_id;
    private String email;
    private String name;
}
