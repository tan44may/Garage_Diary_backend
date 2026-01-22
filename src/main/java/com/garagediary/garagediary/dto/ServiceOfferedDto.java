package com.garagediary.garagediary.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ServiceOfferedDto {
    private UUID id;

    private String serviceName;

    private double price;

    private String description;
}
