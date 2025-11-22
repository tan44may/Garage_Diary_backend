package com.garagediary.garagediary.service;

import com.garagediary.garagediary.dto.LoginDto;
import com.garagediary.garagediary.dto.LoginResponseDto;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    Authentication getAuthentication();
    LoginResponseDto login(LoginDto loginDto);
}
