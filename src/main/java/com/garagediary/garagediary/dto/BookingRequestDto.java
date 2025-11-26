package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.ServiceCenter;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BookingRequestDto {
        private UUID serviceCenter_id;
        private String vehicle_id;
        private String mobile_number;
        private String name;
}
