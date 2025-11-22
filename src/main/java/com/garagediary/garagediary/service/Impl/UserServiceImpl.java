package com.garagediary.garagediary.service.impl;

import com.garagediary.garagediary.Repository.VehicleRepository;
import com.garagediary.garagediary.dto.*;
import com.garagediary.garagediary.entity.UserEntity;
import com.garagediary.garagediary.entity.Vehicle;
import com.garagediary.garagediary.entity.enums.Role;
import com.garagediary.garagediary.Repository.UserRepository;
import com.garagediary.garagediary.service.AuthenticationService;
import com.garagediary.garagediary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final VehicleRepository vehicleRepository;


    private UserEntity convertToEntity(UserRequestDto requestDto)
    {
        return UserEntity.builder()
                .phone(requestDto.getPhone())
                .email(requestDto.getEmail())
                .name(requestDto.getName())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
    }
    private VehicleResponseDto convertVehicleToDto(Vehicle vehicle) {
        return VehicleResponseDto.builder()
                .vehicle_id(vehicle.getVehicle_id())
                .vehicle_number(vehicle.getVehicle_number())
                .brand(vehicle.getBrand())
                .fuel_type(vehicle.getFuel_type())
                .model(vehicle.getModel())
                .type(vehicle.getType())
                .build();
    }
    private UserResponseDto convertToResponse(UserEntity newUser) {
        List<VehicleResponseDto> vehicleDtos = newUser.getVehicles() == null ? List.of() :
                newUser.getVehicles().stream()
                        .map(this::convertVehicleToDto)
                        .collect(Collectors.toList());

        return UserResponseDto.builder()
                .user_id(newUser.getUser_id())
                .phone(newUser.getPhone())
                .role(newUser.getRole())
                .email(newUser.getEmail())
                .name(newUser.getName())
                .vehicles(vehicleDtos)
                .bookings(newUser.getBookings())
                .build();
    }
    @Override
    public UserResponseDto addUser(UserRequestDto requestDto) {

        UserEntity newUser = new UserEntity();
        newUser = convertToEntity(requestDto);
        newUser.setRole(Role.CUSTOMER);
        newUser = userRepository.save(newUser);
        return convertToResponse(newUser);
    }

    @Override
    public BookingResponseDto getBookingById(UUID bookingId) {

        return null;
    }

    @Override
    public UserResponseDto getUserById(UUID userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Unable to find user "));
        return convertToResponse(user);
    }

    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());

        UserEntity updatedUser = userRepository.save(user);

        return convertToResponse(updatedUser);
    }

    @Override
    public UserResponseDto removeUser(UUID userId) {

        UserEntity deletedUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
                userRepository.deleteById(userId);
        return convertToResponse(deletedUser);
    }

    @Override
    public UserResponseDto addNewVehicle(VehicleRequestDto vehicleRequestDto) {
        UUID id = findCurrentUser();
        UserEntity currentUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));

        Vehicle newVehicle = Vehicle.builder()
                .vehicle_number(vehicleRequestDto.getVehicle_number())
                .brand(vehicleRequestDto.getBrand())
                .type(vehicleRequestDto.getType())
                .model(vehicleRequestDto.getModel())
                .fuel_type(vehicleRequestDto.getFuel_type())
                .user(currentUser)
                .build();

        newVehicle = vehicleRepository.save(newVehicle);

        if (currentUser.getVehicles() == null) {
            currentUser.setVehicles(new ArrayList<>());
        }

        currentUser.getVehicles().add(newVehicle);
        userRepository.save(currentUser);

        return convertToResponse(currentUser);
    }


    @Override
    public UserResponseDto removeVehicle(UUID vehicleId) {
        return null;
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        return List.of();
    }

    @Override
    public UUID findCurrentUser() {
        String loggedInUserEmail =  authenticationService.getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(loggedInUserEmail).orElseThrow(()-> new UsernameNotFoundException("User name not found"));
        return user.getUser_id();
    }
}
