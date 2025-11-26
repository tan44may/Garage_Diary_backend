package com.garagediary.garagediary.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.garagediary.garagediary.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue
    private UUID booking_id;
 
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private UserEntity customer;

    @ManyToOne
    @JoinColumn(name = "service_center_id", nullable = false)
    private ServiceCenter serviceCenter;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference
    private Vehicle vehicle;

    private String brand;

    private String fuel;

    private String mobile_number;

    private String name;

//    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
//    private Bill bill;

}
