package com.garagediary.garagediary.service;

import com.garagediary.garagediary.dto.*;
import com.garagediary.garagediary.entity.Vehicle;

import java.util.List;
import java.util.UUID;

public interface UserService {
     RegisterResponseDto addUser(UserRequestDto requestDto);
     BookingResponseDto getBookingById(UUID bookingId);
     UserResponseDto getUserById(UUID userId);
     UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto);
     UserResponseDto removeUser(UUID userId);
     UserResponseDto addNewVehicle(VehicleRequestDto vehicleRequestDto);
     UserResponseDto removeVehicle(UUID vehicleId);
     List<BookingResponseDto> getAllBookings();
     UUID findCurrentUser();
     List<VehicleResponseDto> getAllVehiclesOfUser();
     UserResponseDto findByEmail(String email);
     boolean makeAsFavourite(UUID id);
     List<ServiceCenterResponseDto> listOfFavourites();
}
