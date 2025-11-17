package com.garagediary.garagediary.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "User")
public class User {
    @Id
    private UUID id;
    private String email;
    private String name;
    private String password;
    private int phone;
}
