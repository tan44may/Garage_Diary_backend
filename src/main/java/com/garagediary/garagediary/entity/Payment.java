package com.garagediary.garagediary.entity;

import com.garagediary.garagediary.entity.enums.PaymentMethod;
import com.garagediary.garagediary.entity.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private UUID payment_Id;

    @Column(nullable = false)
    private UUID bill_id;

    @Column(nullable = false)
    private UUID booking;

    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String transaction_id;

    private double amount;

    private PaymentStatus paymentStatus;
}
