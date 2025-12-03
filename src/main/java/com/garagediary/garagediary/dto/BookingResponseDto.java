package com.garagediary.garagediary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.garagediary.garagediary.entity.Bill;
import com.garagediary.garagediary.entity.ServiceCenter;
import com.garagediary.garagediary.entity.UserEntity;
import com.garagediary.garagediary.entity.Vehicle;
import com.garagediary.garagediary.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {
        private UUID booking_id;
        private Status status;
        @JsonIgnore
        private UserDto customer;
        private ServiceCenterDto serviceCenter;
        private VehicleDto vehicle;
        private String mobile_number;
        private String name;
}
