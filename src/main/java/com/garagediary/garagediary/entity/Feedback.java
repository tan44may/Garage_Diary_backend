package com.garagediary.garagediary.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String comment;

    private LocalDate feedbackDate;

    
    @Column(nullable = false)
    private int rating; // rating = 1 to 5

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "service_center_id", nullable = false)
    private ServiceCenter serviceCenter;

    @PrePersist
    public void onCreate() {
        this.feedbackDate = LocalDate.now();
    }
}
