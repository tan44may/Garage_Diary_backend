package com.garagediary.garagediary.entity;

import com.garagediary.garagediary.entity.enums.Role;
import com.sun.jdi.PrimitiveValue;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private UUID user_id;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;
    private String password;
    private int phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
