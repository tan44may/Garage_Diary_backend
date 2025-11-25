package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.UserEntity;
import com.garagediary.garagediary.entity.enums.Vehicle_Type;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleRequestDto {
    private String vehicle_number;
    private String brand;
    private String fuel_type;
    private String model;
    private Vehicle_Type type;
}
