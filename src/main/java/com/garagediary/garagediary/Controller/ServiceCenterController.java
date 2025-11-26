package com.garagediary.garagediary.Controller;

import com.garagediary.garagediary.dto.AvailabilityRequest;
import com.garagediary.garagediary.dto.ServiceCenterRequestDto;
import com.garagediary.garagediary.dto.ServiceCenterResponseDto;
import com.garagediary.garagediary.service.ServiceCenterService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/service-center")
@RequiredArgsConstructor
public class ServiceCenterController {

    private final ServiceCenterService serviceCenterService;

    // ----------------- CREATE -----------------
    @PostMapping
    public ResponseEntity<ServiceCenterResponseDto> createServiceCenter(
            @RequestBody ServiceCenterRequestDto dto) {

        ServiceCenterResponseDto response = serviceCenterService.createServiceCenter(dto);
        return ResponseEntity.ok(response);
    }

    // ----------------- UPDATE -----------------
    @PutMapping("/{id}")
    public ResponseEntity<ServiceCenterResponseDto> updateServiceCenter(
            @PathVariable UUID id,
            @RequestBody ServiceCenterRequestDto dto) {

        ServiceCenterResponseDto response = serviceCenterService.updateServiceCenter(id, dto);
        return ResponseEntity.ok(response);
    }

    // ----------------- DELETE -----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceCenter(@PathVariable UUID id) {
        String message = serviceCenterService.deleteServiceCenter(id);
        return ResponseEntity.ok(message);
    }

    // ----------------- GET BY ID -----------------
    @GetMapping("/{id}")
    public ResponseEntity<ServiceCenterResponseDto> getServiceCenterById(@PathVariable UUID id) {
        ServiceCenterResponseDto response = serviceCenterService.getServiceCenterById(id);
        return ResponseEntity.ok(response);
    }

    // ----------------- GET ALL -----------------
    @GetMapping
    public ResponseEntity<List<ServiceCenterResponseDto>> getAllServiceCenters() {
        List<ServiceCenterResponseDto> response = serviceCenterService.getAllServiceCenters();
        return ResponseEntity.ok(response);
    }

    // ----------------- GET BY OWNER -----------------
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ServiceCenterResponseDto>> getServiceCentersByOwner(
            @PathVariable UUID ownerId) {

        List<ServiceCenterResponseDto> response = serviceCenterService.getServiceCentersByOwner(ownerId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ServiceCenterResponseDto> updateAvailability(@PathVariable UUID id, @RequestBody AvailabilityRequest request)
    {
        return ResponseEntity.ok(serviceCenterService.updateAvailability(request,id));
    }
    // ----------------- UPDATE AVERAGE RATING -----------------
    @PutMapping("/rating/{serviceCenterId}")
    public ResponseEntity<Double> updateAverageRating(@PathVariable UUID serviceCenterId) {
        Double updatedRating = serviceCenterService.updateAverageRating(serviceCenterId);
        return ResponseEntity.ok(updatedRating);
    }
}
