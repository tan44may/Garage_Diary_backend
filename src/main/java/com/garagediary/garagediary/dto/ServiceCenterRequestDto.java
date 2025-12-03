package com.garagediary.garagediary.dto;

import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCenterRequestDto {

    private String garageName;
    private double latitude;
    private double longitude;
    private String phone;

    private String email;

    private List<String> availableDays;
    private LocalTime startTime;
    private LocalTime endTime;

    private String profileUrl;
    private String coverImgUrl;
    private List<String> gallery;
    private List<String> socialMedia;
    private List<String> documents;
}
