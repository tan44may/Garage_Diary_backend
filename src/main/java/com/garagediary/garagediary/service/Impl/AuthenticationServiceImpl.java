package com.garagediary.garagediary.service.impl;

import com.garagediary.garagediary.Repository.UserRepository;
import com.garagediary.garagediary.dto.LoginDto;
import com.garagediary.garagediary.dto.LoginResponseDto;
import com.garagediary.garagediary.entity.UserEntity;
import com.garagediary.garagediary.service.AuthenticationService;
import com.garagediary.garagediary.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService appUserDetailsService;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public LoginResponseDto login(LoginDto request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final UserDetails userDetails = appUserDetailsService.loadUserByUsername(request.getEmail());
        final String jwtToken = jwtUtils.generateToken(userDetails);
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NoSuchElementException("Unable to find user with email :"+request.getEmail()));
        return new LoginResponseDto(
                user.getUser_id(),
                user.getName(),
                user.getEmail(),
                jwtToken,
                user.getRole().name());

    }
}
