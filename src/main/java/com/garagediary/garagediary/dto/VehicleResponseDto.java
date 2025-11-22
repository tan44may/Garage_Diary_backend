package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.enums.Vehicle_Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDto {
    private UUID vehicle_id;
    private String vehicle_number;
    private String brand;
    private String fuel_type;
    private String model;
    private Vehicle_Type type;
}
