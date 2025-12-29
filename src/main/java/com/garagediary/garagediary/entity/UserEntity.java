package com.garagediary.garagediary.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.garagediary.garagediary.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID user_id;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;
    private String password;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Booking> bookings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Vehicle> vehicles;

    private String image;
    
    @ElementCollection
    @CollectionTable(name = "user_favourites", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "service_center_id")
    private List<UUID> favourites;



}
