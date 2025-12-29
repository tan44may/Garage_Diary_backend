package com.garagediary.garagediary.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garagediary.garagediary.dto.*;
import com.garagediary.garagediary.service.ServiceCenterService;
import com.garagediary.garagediary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/service-center")
@RequiredArgsConstructor
public class ServiceCenterController {

    private final ServiceCenterService serviceCenterService;
    private final UserService userService;

    @Autowired
    private ObjectMapper mapper;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ServiceCenterResponseDto> createServiceCenter(
            @RequestPart("serviceCenterRequestDto") String dtoJson,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestPart(value = "coverImage", required = false) MultipartFile coverImage
    ) throws IOException {

        ServiceCenterRequestDto serviceCenterRequestDto =
                mapper.readValue(dtoJson, ServiceCenterRequestDto.class);

        return ResponseEntity.ok(
                serviceCenterService.createServiceCenter(serviceCenterRequestDto, profileImage, coverImage)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<ServiceCenterResponseDto> updateServiceCenter(
            @PathVariable UUID id,
            @RequestBody ServiceCenterRequestDto dto) {

        ServiceCenterResponseDto response = serviceCenterService.updateServiceCenter(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceCenter(@PathVariable UUID id) {
        String message = serviceCenterService.deleteServiceCenter(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceCenterResponseDto> getServiceCenterById(@PathVariable UUID id) {
        ServiceCenterResponseDto response = serviceCenterService.getServiceCenterById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ServiceCenterResponseDto>> getAllServiceCenters() {
        List<ServiceCenterResponseDto> response = serviceCenterService.getAllServiceCenters();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ServiceCenterResponseDto>> getServiceCentersByOwner(
            @PathVariable UUID ownerId) {

        List<ServiceCenterResponseDto> response = serviceCenterService.getServiceCentersByOwner(ownerId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ServiceCenterResponseDto> updateAvailability(@PathVariable UUID id, @RequestBody AvailabilityRequest request) {
        return ResponseEntity.ok(serviceCenterService.updateAvailability(request, id));
    }

    @PutMapping("/rating/{serviceCenterId}")
    public ResponseEntity<Double> updateAverageRating(@PathVariable UUID serviceCenterId) {
        Double updatedRating = serviceCenterService.updateAverageRating(serviceCenterId);
        return ResponseEntity.ok(updatedRating);
    }

    @PutMapping("/plan/{id}")
    public ResponseEntity<ServiceCenterResponseDto> updatePlan(@PathVariable UUID id, @RequestBody Plan dto) {
        return ResponseEntity.ok(serviceCenterService.updatePlan(id, dto.getPlan()));
    }
}
