package com.garagediary.garagediary.service;

import com.garagediary.garagediary.dto.UserResponseDto;
import com.garagediary.garagediary.dto.VehicleRequestDto;

import java.util.UUID;

public interface VehicleService {
    UserResponseDto getOwner(UUID vehicle_number);
    VehicleRequestDto getVehicleByNumber(String vehicleNumber);
}
