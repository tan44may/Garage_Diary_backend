package com.garagediary.garagediary.entity;

import com.garagediary.garagediary.entity.enums.ActivePlan;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import com.garagediary.garagediary.entity.ServiceOffered;
import com.garagediary.garagediary.entity.Booking;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String garageName;
    private double latitude;
    private double longitude;
    private String phone;

    private UUID ownerId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ServiceOffered> services;

    @Enumerated(EnumType.STRING)
    private ActivePlan activePlan;

    private LocalDate planStartedDate;
    private LocalDate planEndDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @ElementCollection
    private List<String> availableDays;

    private LocalTime startTime;
    private LocalTime endTime;

    private float averageRating;

    
    private String profileUrl;         
    private String coverImgUrl;        

    @ElementCollection
    private List<String> gallery;      

    @ElementCollection
    private List<String> socialMedia;  
}
