package com.garagediary.garagediary.Controller;

import com.garagediary.garagediary.dto.UserRequestDto;
import com.garagediary.garagediary.dto.UserResponseDto;
import com.garagediary.garagediary.dto.VehicleRequestDto;
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

    @PostMapping("/addVehicle")
    public ResponseEntity<UserResponseDto> addVehicle(@RequestBody VehicleRequestDto requestDto)
    {
        return ResponseEntity.ok(userService.addNewVehicle(requestDto));
    }

    @DeleteMapping("/removeVehicle/{id}")
    ResponseEntity<UserResponseDto> removeVehicle(@PathVariable UUID id)
    {
        return new ResponseEntity<>(userService.removeVehicle(id), HttpStatus.OK);
    }
}

