package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.ServiceCenter;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRequestDto {
        private ServiceCenter serviceCenter;
        private String vehicle_id;
        private String brand;
        private String fuel;
        private int mobile_number;
        private String name;
}
