package com.garagediary.garagediary.Controller;

import com.garagediary.garagediary.dto.*;
import com.garagediary.garagediary.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/email/{email}")
    ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email)
    {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
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

    @GetMapping("/me/all-vehicles")
    public ResponseEntity<List<VehicleResponseDto>> getAllVehicles()
    {
        return ResponseEntity.ok(userService.getAllVehiclesOfUser());
    }

    @PostMapping("/make-favourite/{id}")
    public ResponseEntity<Boolean> makeFavourite(@PathVariable UUID id )
    {
        return ResponseEntity.ok(userService.makeAsFavourite(id));
    }

    @GetMapping("/list-favourite")
    public ResponseEntity<List<ServiceCenterResponseDto>> listOfFavourite()
    {
        return ResponseEntity.ok(userService.listOfFavourites());
    }
}

