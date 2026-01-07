package com.garagediary.garagediary.service.impl;

import com.garagediary.garagediary.Repository.ServiceCenterRepository;
import com.garagediary.garagediary.Repository.ServiceOfferedRepository;
import com.garagediary.garagediary.Repository.UserRepository;
import com.garagediary.garagediary.dto.AvailabilityRequest;
import com.garagediary.garagediary.dto.ServiceCenterRequestDto;
import com.garagediary.garagediary.dto.ServiceCenterResponseDto;
import com.garagediary.garagediary.entity.ServiceCenter;
import com.garagediary.garagediary.entity.ServiceOffered;
import com.garagediary.garagediary.service.ServiceCenterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.util.StringUtils;

import java.io.File;

@Service
@AllArgsConstructor
public class ServiceCenterServiceImpl implements ServiceCenterService {

    private final ServiceCenterRepository serviceCenterRepository;
    private final ServiceOfferedRepository serviceOfferedRepository;

    private static final String FILE_DIRECTORY =
            System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "serviceCenterImages";
    private static final List<String> ALLOWED_FILE_EXTENSIONS = List.of("jpg", "jpeg", "png");

    private String saveFile(MultipartFile file) throws IOException {
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if (fileExtension == null || !ALLOWED_FILE_EXTENSIONS.contains(fileExtension.toLowerCase())) {
            throw new IOException("Invalid file type.");
        }

        String uniqueFilename = UUID.randomUUID() + "." + fileExtension;

        File directory = new File(FILE_DIRECTORY);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Could not create directory: " + FILE_DIRECTORY);
        }

        File serverFile = new File(directory, uniqueFilename);
        file.transferTo(serverFile);

        return uniqueFilename;
    }

    @Override
    public ServiceCenterResponseDto createServiceCenter(ServiceCenterRequestDto dto, MultipartFile adharCard, MultipartFile panCard, MultipartFile shopact) throws IOException {
        ServiceCenter serviceCenter = new ServiceCenter();
        serviceCenter.setGarageName(dto.getGarageName());
        serviceCenter.setLatitude(dto.getLatitude());
        serviceCenter.setLongitude(dto.getLongitude());
        serviceCenter.setPhone(dto.getPhone());
        serviceCenter.setAvailableDays(dto.getAvailableDays());
        serviceCenter.setStartTime(dto.getStartTime());
        serviceCenter.setEndTime(dto.getEndTime());
        serviceCenter.setGallery(dto.getGallery());
        serviceCenter.setSocialMedia(dto.getSocialMedia());
//        serviceCenter.setDocuments(dto.getDocuments());

        // Use URLs if passed as Strings
//        serviceCenter.setProfileUrl(dto.getProfileUrl());
//        serviceCenter.setCoverImgUrl(dto.getCoverImgUrl());

        // Save images if uploaded as files
        if (adharCard != null && !adharCard.isEmpty()) {
            String adharCardUrl = saveFile(adharCard);
            serviceCenter.setAdharCard("/" + FILE_DIRECTORY + "/" + adharCardUrl);
        }
        if (panCard != null && !panCard.isEmpty()) {
            String panCardUrl = saveFile(panCard);
            serviceCenter.setPanCard("/" + FILE_DIRECTORY + "/" + panCardUrl);
        }

        if (shopact != null && !shopact.isEmpty()) {
            String shopactUrl = saveFile(shopact);
            serviceCenter.setShopact("/" + FILE_DIRECTORY + "/" + shopactUrl);
        }

        // Save to database
        serviceCenter =serviceCenterRepository.save(serviceCenter);
        return mapEntityToResponse(serviceCenter);
    }

    // ---------------- UPDATE ---------------------
    @Override
    public ServiceCenterResponseDto updateServiceCenter(UUID id, ServiceCenterRequestDto dto) {

        ServiceCenter sc = serviceCenterRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Service center not found with id :" + id));

        mapDtoToEntity(dto, sc);

        ServiceCenter updated = serviceCenterRepository.save(sc);
        return mapEntityToResponse(updated);
    }

    // ---------------- DELETE ---------------------
    @Override
    public String deleteServiceCenter(UUID id) {

        if (!serviceCenterRepository.existsById(id)) {
            throw new NoSuchElementException("Service center not found with id :" + id);
        }

        serviceCenterRepository.deleteById(id);
        return "Service Center deleted successfully";
    }

    // ---------------- GET BY ID ---------------------
    @Override
    public ServiceCenterResponseDto getServiceCenterById(UUID id) {

        ServiceCenter sc = serviceCenterRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Service center not found with id :" + id));

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
                .orElseThrow(() -> new NoSuchElementException("Service center not found with id :" + serviceCenterId));

        // Agar tumne feedback system add kiya hoga to yaha average calculate hoga
        // Filhal calculate nahi kar rahe, direct return kar dete:
        return (double) sc.getAverageRating();
    }

    @Override
    public ServiceCenterResponseDto updateAvailability(AvailabilityRequest request, UUID id) {
        ServiceCenter center = serviceCenterRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Service center not found with id :" + id));
        center.setAvailableDays(request.getAvailableDays());
        center.setEndTime(request.getEndTime());
        center.setStartTime(request.getStartTime());
        center = serviceCenterRepository.save(center);
        return mapEntityToResponse(center);
    }

    @Override
    public ServiceCenterResponseDto updatePlan(UUID id, String planName) {
        ServiceCenter center = serviceCenterRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Service center not found with id :" + id));

        center.setPlan(planName);
        center = serviceCenterRepository.save(center);

        return mapEntityToResponse(center);
    }

    @Override
    public ServiceCenterResponseDto addService(UUID serviceCenterId, ServiceOffered service) {
        ServiceCenter center = serviceCenterRepository.findById(serviceCenterId)
                .orElseThrow(() -> new NoSuchElementException("Service center not found with id :" + serviceCenterId));

//        service.setServiceCenter(center);
        service = serviceOfferedRepository.save(service);
        center.getServices().add(service);

        center = serviceCenterRepository.save(center);
        return mapEntityToResponse(center);
    }

    @Override
    public List<ServiceOffered> getOfferedServices(UUID serviceCenterId) {
        ServiceCenter center = serviceCenterRepository.findById(serviceCenterId)
                .orElseThrow(() -> new NoSuchElementException("Service center not found with id :" + serviceCenterId));

        return center.getServices();
    }

    @Override
    public Page<ServiceCenterResponseDto> searchNearbyGarages(
            double userLat,
            double userLng,
            double radiusInKm,
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return serviceCenterRepository
                .findNearbyServiceCenters(userLat, userLng, radiusInKm, pageable)
                .map(this::mapEntityToResponse);
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
                .services(sc.getServices())
                .plan(sc.getPlan())
                .documents(sc.getDocuments())
                .startTime(sc.getStartTime())
                .endTime(sc.getEndTime())
                .averageRating(sc.getAverageRating())
                .profileUrl(sc.getProfileUrl())
                .coverImgUrl(sc.getCoverImgUrl())
                .adharCard(sc.getAdharCard())
                .panCard(sc.getPanCard())
                .shopact(sc.getShopact())
                .gallery(sc.getGallery())
                .socialMedia(sc.getSocialMedia())
                .totalServices(sc.getServices() != null ? sc.getServices().size() : 0)
                .totalBookings(sc.getBookings() != null ? sc.getBookings().size() : 0)
                .build();
    }
}
