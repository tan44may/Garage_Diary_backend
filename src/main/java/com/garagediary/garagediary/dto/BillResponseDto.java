package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.Booking;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class BillResponseDto {
        private UUID bill_id;
        private Booking booking;
        private UUID service_center_id;
        private double discount;
        private LocalDateTime generated_date;
        private double subtotal;
        private double tax;
        private double total_amount;
}
