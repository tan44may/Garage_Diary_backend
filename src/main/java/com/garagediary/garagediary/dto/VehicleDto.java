package com.garagediary.garagediary.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class VehicleDto {
    private UUID vehicle_id;
    private String vehicle_number;
    private String brand;
}
