package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
        private String email;
        private String name;
        private String password;
        private String phone;
        private Role role;
}
