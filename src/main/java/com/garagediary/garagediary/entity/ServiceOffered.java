package com.garagediary.garagediary.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class ServiceOffered {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String serviceName;

    private double price;

    private String description;

    // MANY SERVICES CAN BELONG TO ONE SERVICE CENTER
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "service_center_id", nullable = false)
//    private ServiceCenter serviceCenter;

}
