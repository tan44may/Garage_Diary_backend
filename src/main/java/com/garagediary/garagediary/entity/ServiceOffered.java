package com.garagediary.garagediary.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ServiceOffered {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String serviceName;

    private double price;

    private String description;

}
