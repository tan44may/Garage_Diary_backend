package com.garagediary.garagediary.service;

import com.garagediary.garagediary.dto.BookingResponseDto;
import com.garagediary.garagediary.dto.UserRequestDto;
import com.garagediary.garagediary.dto.UserResponseDto;
import com.garagediary.garagediary.entity.Vehicle;

import java.util.List;
import java.util.UUID;

public interface UserService {
     UserResponseDto addUser(UserRequestDto requestDto);
     BookingResponseDto getBookingById(UUID bookingId);
     UserResponseDto getUserById(UUID userId);
     UserResponseDto updateUser(UUID userId);
     UserResponseDto removeUser(UUID userId);
     UserResponseDto addNewVehicle(Vehicle vehicle);
     UserResponseDto removeVehicle(UUID vehicleId);
     List<BookingResponseDto> getAllBookings();
}
