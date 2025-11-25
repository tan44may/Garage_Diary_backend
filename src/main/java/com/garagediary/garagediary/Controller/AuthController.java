package com.garagediary.garagediary.Controller;

import com.garagediary.garagediary.dto.*;
import com.garagediary.garagediary.service.AuthenticationService;
import com.garagediary.garagediary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginDto request)
    {
        return authenticationService.login(request);
    }

    @PostMapping("/register")
    ResponseEntity<RegisterResponseDto> addUser(@RequestBody UserRequestDto requestDto)
    {
        return new ResponseEntity<>(userService.addUser(requestDto), HttpStatus.OK);
    }
}
