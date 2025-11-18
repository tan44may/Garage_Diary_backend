package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.Booking;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class BillRequestDto {
        private Booking booking;
        private UUID service_center_id;
        private double subtotal;
        private double total_amount;
}
