package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.Bill;
import com.garagediary.garagediary.entity.ServiceCenter;
import com.garagediary.garagediary.entity.UserEntity;
import com.garagediary.garagediary.entity.Vehicle;
import com.garagediary.garagediary.entity.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class BookingResponseDto {
        private UUID booking_id;
        private Status status;
        private UserDto customer;
        private ServiceCenterDto serviceCenter;
        private VehicleDto vehicle;
        private String mobile_number;
        private String name;
}
