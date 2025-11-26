package com.garagediary.garagediary.service;

import com.garagediary.garagediary.dto.UserDto;
import com.garagediary.garagediary.dto.UserResponseDto;
import com.garagediary.garagediary.dto.VehicleRequestDto;

import java.util.UUID;

public interface VehicleService {
    UserDto getOwner(UUID vehicle_id);

}
