package com.garagediary.garagediary.service;

import com.garagediary.garagediary.dto.AvailabilityRequest;
import com.garagediary.garagediary.dto.ServiceCenterRequestDto;
import com.garagediary.garagediary.dto.ServiceCenterResponseDto;
import org.springframework.boot.availability.AvailabilityState;

import java.util.List;
import java.util.UUID;

public interface ServiceCenterService {

    ServiceCenterResponseDto createServiceCenter(ServiceCenterRequestDto dto);

    ServiceCenterResponseDto updateServiceCenter(UUID id, ServiceCenterRequestDto dto);

    ServiceCenterResponseDto getServiceCenterById(UUID id);

    List<ServiceCenterResponseDto> getAllServiceCenters();

    List<ServiceCenterResponseDto> getServiceCentersByOwner(UUID ownerId);

    String deleteServiceCenter(UUID id);

    Double updateAverageRating(UUID serviceCenterId);

    ServiceCenterResponseDto updateAvailability(AvailabilityRequest request,UUID id);
}
