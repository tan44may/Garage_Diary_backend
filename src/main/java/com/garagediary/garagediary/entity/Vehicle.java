package com.garagediary.garagediary.entity;

import com.garagediary.garagediary.entity.enums.Vehicle_Type;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.UUID;

@Entity
@Builder
public class Vehicle {
    @Id
    @GeneratedValue
    private UUID vehicle_id;

    @Column(unique = true)
    private String vehicle_number;

    private String brand;
    private String fuel_type;
    private String model;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private Vehicle_Type type;
}
