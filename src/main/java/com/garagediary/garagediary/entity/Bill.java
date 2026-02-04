package com.garagediary.garagediary.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "Bill")
public class Bill {

    @Id
    @GeneratedValue
    private UUID bill_id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private Booking booking;

    @Column(nullable = false)
    private UUID service_center_id;

    private double discount;

    private LocalDateTime generated_date;

    private double subtotal;

    private double tax;

    private double total_amount;
}
