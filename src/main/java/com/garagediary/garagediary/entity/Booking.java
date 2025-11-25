package com.garagediary.garagediary.entity;

import com.garagediary.garagediary.entity.enums.Status;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Booking {
    @Id
    @GeneratedValue
    private UUID booking_id;
 
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private UserEntity customer;

    @ManyToOne
    @JoinColumn(name = "service_center_id", nullable = false)
    private ServiceCenter serviceCenter;

    @Column(nullable = false)
    private String vehicle_id;

    private String brand;

    private String fuel;

    private int mobile_number;

    private String name;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Bill bill;

}
