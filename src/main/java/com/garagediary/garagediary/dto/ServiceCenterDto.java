package com.garagediary.garagediary.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ServiceCenterDto {
    private UUID id;
    private String garageName;
    private String phone;
}
