package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.Bill;
import com.garagediary.garagediary.entity.ServiceCenter;
import com.garagediary.garagediary.entity.User;
import com.garagediary.garagediary.entity.enums.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class BookingResponseDto {
    private UUID booking_id;

    private Status status;

    private User customer;

    private ServiceCenter serviceCenter;
    private String vehicle_id;

    private String brand;

    private String fuel;

    private int mobile_number;

    private String name;
    private Bill bill;
}
