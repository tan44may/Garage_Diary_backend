package com.garagediary.garagediary.service;

import com.garagediary.garagediary.dto.AvailabilityRequest;
import com.garagediary.garagediary.dto.ServiceCenterRequestDto;
import com.garagediary.garagediary.dto.ServiceCenterResponseDto;
import com.garagediary.garagediary.dto.ServiceOfferedDto;
import com.garagediary.garagediary.entity.ServiceCenter;
import com.garagediary.garagediary.entity.ServiceOffered;
import org.springframework.boot.availability.AvailabilityState;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ServiceCenterService {

    ServiceCenterResponseDto createServiceCenter(ServiceCenterRequestDto dto, MultipartFile adharCard, MultipartFile panCard,MultipartFile shopact) throws IOException;

    ServiceCenterResponseDto updateServiceCenter(UUID id, ServiceCenterRequestDto dto);

    ServiceCenterResponseDto getServiceCenterById(UUID id);

    List<ServiceCenterResponseDto> getAllServiceCenters();

    List<ServiceCenterResponseDto> getServiceCentersByOwner(UUID ownerId);

    String deleteServiceCenter(UUID id);

    Double updateAverageRating(UUID serviceCenterId);

    ServiceCenterResponseDto updateAvailability(AvailabilityRequest request,UUID id);

    ServiceCenterResponseDto updatePlan(UUID id,String planName );

    ServiceCenterResponseDto addService(UUID serviceCenterId, ServiceOffered service);

    List<ServiceOfferedDto> getOfferedServices(UUID serviceCenterId);

    Page<ServiceCenterResponseDto> searchNearbyGarages(double userLat, double userLng, double radiusInKm, int page, int size, String sortBy, String direction);

    ServiceCenterResponseDto getServiceCenterByEmail(String email);
}
