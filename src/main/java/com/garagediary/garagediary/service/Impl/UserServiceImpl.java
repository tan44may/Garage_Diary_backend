package com.garagediary.garagediary.service.Impl;

import com.garagediary.garagediary.dto.BookingResponseDto;
import com.garagediary.garagediary.dto.UserRequestDto;
import com.garagediary.garagediary.dto.UserResponseDto;
import com.garagediary.garagediary.dto.VehicleRequestDto;
import com.garagediary.garagediary.entity.User;
import com.garagediary.garagediary.entity.Vehicle;
import com.garagediary.garagediary.entity.enums.Role;
import com.garagediary.garagediary.repository.UserRepository;
import com.garagediary.garagediary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private User convertToEntity(UserRequestDto requestDto)
    {
        return User.builder()
                .phone(requestDto.getPhone())
                .email(requestDto.getEmail())
                .name(requestDto.getName())
                //TODO adding encoded password after implementing spring security
                .password(requestDto.getPassword())
                .build();
    }
    private UserResponseDto convertToResponse(User newUser)
    {
        return UserResponseDto.builder()
                .user_id(newUser.getUser_id())
                .phone(newUser.getPhone())
                .role(newUser.getRole())
                .email(newUser.getEmail())
                .name(newUser.getName())
                .vehicles(newUser.getVehicles())
                .bookings(newUser.getBookings())
                .build();
    }
    @Override
    public UserResponseDto addUser(UserRequestDto requestDto) {

        User newUser = new User();
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
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Unable to find user "));
        return convertToResponse(user);
    }

    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());

        User updatedUser = userRepository.save(user);

        return convertToResponse(updatedUser);
    }

    @Override
    public UserResponseDto removeUser(UUID userId) {

        User deletedUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
                userRepository.deleteById(userId);
        return convertToResponse(deletedUser);
    }

    @Override
    public UserResponseDto addNewVehicle(VehicleRequestDto vehicleRequestDto) {

        Vehicle newVehicle = Vehicle.builder()
                .vehicle_number(vehicleRequestDto.getVehicle_number())
                .brand(vehicleRequestDto.getBrand())
                .type(vehicleRequestDto.getType())
                .model(vehicleRequestDto.getModel())
                .fuel_type(vehicleRequestDto.getFuel_type())
                //TODO adding current logged in user after implementing spring security
                .user(vehicleRequestDto.getUser())
                .build();

        return null;
    }

    @Override
    public UserResponseDto removeVehicle(UUID vehicleId) {
        return null;
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        return List.of();
    }
}
