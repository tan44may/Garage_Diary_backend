package com.garagediary.garagediary.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.garagediary.garagediary.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    /* -------- STATUS -------- */
    @Enumerated(EnumType.STRING)
    private Status status; // PENDING, IN_PROGRESS, COMPLETED, CANCELLED

    /* -------- RELATIONS -------- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private UserEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_center_id", nullable = false)
    private ServiceCenter serviceCenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceOffered service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    /* -------- BOOKING DETAILS -------- */
    private LocalDate bookingDate;
    private LocalTime bookingTime;

    private String mobileNumber;
    private String customerName;


}
