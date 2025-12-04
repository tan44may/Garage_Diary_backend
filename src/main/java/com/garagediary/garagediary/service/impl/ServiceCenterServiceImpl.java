package com.garagediary.garagediary.service.impl;

import com.garagediary.garagediary.Repository.ServiceCenterRepository;
import com.garagediary.garagediary.Repository.UserRepository;
import com.garagediary.garagediary.dto.AvailabilityRequest;
import com.garagediary.garagediary.dto.ServiceCenterRequestDto;
import com.garagediary.garagediary.dto.ServiceCenterResponseDto;
import com.garagediary.garagediary.entity.ServiceCenter;
import com.garagediary.garagediary.entity.UserEntity;
import com.garagediary.garagediary.service.ServiceCenterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceCenterServiceImpl implements ServiceCenterService {

    private final ServiceCenterRepository serviceCenterRepository;
    private final UserRepository userRepository;

    // ---------------- CREATE ---------------------
    @Override
    public ServiceCenterResponseDto createServiceCenter(ServiceCenterRequestDto dto) {

        ServiceCenter serviceCenter = new ServiceCenter();
        UserEntity user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new NoSuchElementException("User not found"));
        serviceCenter.setOwnerId(user.getUser_id());
        mapDtoToEntity(dto, serviceCenter);

        ServiceCenter saved = serviceCenterRepository.save(serviceCenter);
        return mapEntityToResponse(saved);
    }

    // ---------------- UPDATE ---------------------
    @Override
    public ServiceCenterResponseDto updateServiceCenter(UUID id, ServiceCenterRequestDto dto) {

        ServiceCenter sc = serviceCenterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service Center not found"));

        mapDtoToEntity(dto, sc);

        ServiceCenter updated = serviceCenterRepository.save(sc);
        return mapEntityToResponse(updated);
    }

    // ---------------- DELETE ---------------------
    @Override
    public String deleteServiceCenter(UUID id) {

        if (!serviceCenterRepository.existsById(id)) {
            throw new RuntimeException("Service Center not found");
        }

        serviceCenterRepository.deleteById(id);
        return "Service Center deleted successfully";
    }

    // ---------------- GET BY ID ---------------------
    @Override
    public ServiceCenterResponseDto getServiceCenterById(UUID id) {

        ServiceCenter sc = serviceCenterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service Center not found"));

        return mapEntityToResponse(sc);
    }

    // ---------------- GET ALL ---------------------
    @Override
    public List<ServiceCenterResponseDto> getAllServiceCenters() {

        return serviceCenterRepository.findAll()
                .stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    // ---------------- GET BY OWNER ID ---------------------
    @Override
    public List<ServiceCenterResponseDto> getServiceCentersByOwner(UUID ownerId) {

        return serviceCenterRepository.findByOwnerId(ownerId)
                .stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    // ---------------- UPDATE AVERAGE RATING ---------------------
    @Override
    public Double updateAverageRating(UUID serviceCenterId) {

        ServiceCenter sc = serviceCenterRepository.findById(serviceCenterId)
                .orElseThrow(() -> new RuntimeException("Service Center not found"));

        // Agar tumne feedback system add kiya hoga to yaha average calculate hoga
        // Filhal calculate nahi kar rahe, direct return kar dete:
        return (double) sc.getAverageRating();
    }

    @Override
    public ServiceCenterResponseDto updateAvailability(AvailabilityRequest request, UUID id) {
        ServiceCenter center = serviceCenterRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Service center not found"));
        center.setAvailableDays(request.getAvailableDays());
        center.setEndTime(request.getEndTime());
        center.setStartTime(request.getStartTime());
        center = serviceCenterRepository.save(center);
        return mapEntityToResponse(center);
    }

    @Override
    public ServiceCenterResponseDto updatePlan(UUID id, String planName) {
        ServiceCenter center = serviceCenterRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Service center not found"));

        center.setPlan(planName);
        center = serviceCenterRepository.save(center);

        return mapEntityToResponse(center);
    }

    // -------------------------------------------------------------------
    //                     HELPER MAPPING METHODS
    // -------------------------------------------------------------------

    private void mapDtoToEntity(ServiceCenterRequestDto dto, ServiceCenter sc) {
        sc.setGarageName(dto.getGarageName());
        sc.setLatitude(dto.getLatitude());
        sc.setLongitude(dto.getLongitude());
        sc.setPhone(dto.getPhone());
        sc.setDocuments(dto.getDocuments());
        sc.setAvailableDays(dto.getAvailableDays());
        sc.setStartTime(dto.getStartTime());
        sc.setEndTime(dto.getEndTime());
        sc.setProfileUrl(dto.getProfileUrl());
        sc.setCoverImgUrl(dto.getCoverImgUrl());
        sc.setGallery(dto.getGallery());
        sc.setSocialMedia(dto.getSocialMedia());
    }

    private ServiceCenterResponseDto mapEntityToResponse(ServiceCenter sc) {
        return ServiceCenterResponseDto.builder()
                .id(sc.getId())
                .garageName(sc.getGarageName())
                .latitude(sc.getLatitude())
                .longitude(sc.getLongitude())
                .phone(sc.getPhone())
                .ownerId(sc.getOwnerId())
                .activePlan(sc.getActivePlan())
                .planStartedDate(sc.getPlanStartedDate())
                .planEndDate(sc.getPlanEndDate())
                .availableDays(sc.getAvailableDays())
                .plan(sc.getPlan())
                .documents(sc.getDocuments())
                .startTime(sc.getStartTime())
                .endTime(sc.getEndTime())
                .averageRating(sc.getAverageRating())
                .profileUrl(sc.getProfileUrl())
                .coverImgUrl(sc.getCoverImgUrl())
                .gallery(sc.getGallery())
                .socialMedia(sc.getSocialMedia())
                .totalServices(sc.getServices() != null ? sc.getServices().size() : 0)
                .totalBookings(sc.getBookings() != null ? sc.getBookings().size() : 0)
                .build();
    }
}
