package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.User;
import com.garagediary.garagediary.entity.enums.Vehicle_Type;
import lombok.Data;

import java.util.UUID;

@Data
public class VehicleResponseDto {
    private UUID vehicle_id;
    private String vehicle_number;
    private String brand;
    private String fuel_type;
    private String model;
    private Vehicle_Type type;
    private User user;
}
