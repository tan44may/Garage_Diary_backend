package com.garagediary.garagediary.controller;

import com.garagediary.garagediary.dto.UserRequestDto;
import com.garagediary.garagediary.dto.UserResponseDto;
import com.garagediary.garagediary.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto requestDto)
    {
        return new ResponseEntity<>(userService.addUser(requestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id)
    {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id, @RequestBody UserRequestDto userRequestDto) {

        return ResponseEntity.ok(userService.updateUser(id, userRequestDto));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<UserResponseDto> deleteUserById(@PathVariable UUID id)
    {
        return new ResponseEntity<>(userService.removeUser(id), HttpStatus.OK);
    }

}

