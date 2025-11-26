package com.garagediary.garagediary.service.impl;

import com.garagediary.garagediary.Repository.VehicleRepository;
import com.garagediary.garagediary.dto.UserDto;
import com.garagediary.garagediary.dto.UserResponseDto;
import com.garagediary.garagediary.dto.VehicleRequestDto;
import com.garagediary.garagediary.entity.UserEntity;
import com.garagediary.garagediary.entity.Vehicle;
import com.garagediary.garagediary.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    public UserDto getOwner(UUID vehicle_id) {

        Vehicle vehicle = vehicleRepository.findById(vehicle_id).orElseThrow(()-> new NoSuchElementException("Vehicle not found"));
        UserEntity user = vehicle.getUser();

        return UserDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .user_id(user.getUser_id())
                .build();
    }


}
