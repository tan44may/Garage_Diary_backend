package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.Booking;
import com.garagediary.garagediary.entity.Vehicle;
import com.garagediary.garagediary.entity.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserResponseDto {
        private UUID user_id;
        private String email;
        private String name;
        private String phone;
        private Role role;
        private String image;
        private List<VehicleResponseDto> vehicles;
        private List<BookingResponseDto> bookings;
}
