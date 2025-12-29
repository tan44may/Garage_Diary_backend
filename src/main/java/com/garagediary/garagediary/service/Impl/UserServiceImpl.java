package com.garagediary.garagediary.service.impl;

import com.garagediary.garagediary.Repository.ServiceCenterRepository;
import com.garagediary.garagediary.Repository.UserRepository;
import com.garagediary.garagediary.Repository.VehicleRepository;
import com.garagediary.garagediary.dto.*;
import com.garagediary.garagediary.entity.Booking;
import com.garagediary.garagediary.entity.ServiceCenter;
import com.garagediary.garagediary.entity.UserEntity;
import com.garagediary.garagediary.entity.Vehicle;
import com.garagediary.garagediary.service.AuthenticationService;
import com.garagediary.garagediary.service.UserService;
import com.garagediary.garagediary.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final VehicleRepository vehicleRepository;
    private final AppUserDetailsService appUserDetailsService;
    private final ServiceCenterRepository serviceCenterRepository;
    private final JwtUtils jwtUtils;
    private final ModelMapper modelMapper;

    @Override
    public RegisterResponseDto addUser(UserRequestDto requestDto) {

        UserEntity newUser = convertToEntity(requestDto);
        newUser = userRepository.save(newUser);

        final UserDetails userDetails = appUserDetailsService.loadUserByUsername(requestDto.getEmail());
        final String jwtToken = jwtUtils.generateToken(userDetails);

        return new RegisterResponseDto(newUser.getUser_id(), newUser.getEmail(), newUser.getName(), newUser.getPhone(), newUser.getRole(), jwtToken);
    }

    @Override
    public BookingResponseDto getBookingById(UUID bookingId) {

        return null;
    }

    @Override
    public UserResponseDto getUserById(UUID userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Unable to find user "));
        return convertToResponse(user);
    }

    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());

        UserEntity updatedUser = userRepository.save(user);

        return convertToResponse(updatedUser);
    }

    @Override
    public UserResponseDto removeUser(UUID userId) {

        UserEntity deletedUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        userRepository.deleteById(userId);
        return convertToResponse(deletedUser);
    }

    @Override
    public UserResponseDto addNewVehicle(VehicleRequestDto vehicleRequestDto) {
        UUID id = findCurrentUser();
        UserEntity currentUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));

        Vehicle newVehicle = Vehicle.builder()
                .vehicle_number(vehicleRequestDto.getVehicle_number())
                .brand(vehicleRequestDto.getBrand())
                .type(vehicleRequestDto.getType())
                .model(vehicleRequestDto.getModel())
                .fuel_type(vehicleRequestDto.getFuel_type())
                .user(currentUser)
                .build();

        newVehicle = vehicleRepository.save(newVehicle);

        if (currentUser.getVehicles() == null) {
            currentUser.setVehicles(new ArrayList<>());
        }

        currentUser.getVehicles().add(newVehicle);
        userRepository.save(currentUser);

        return convertToResponse(currentUser);
    }

    @Override
    public UserResponseDto removeVehicle(UUID vehicleId) {

        UUID currentUserId = findCurrentUser();
        UserEntity currentUser = userRepository.findById(currentUserId).orElseThrow(() -> new NoSuchElementException("User not found"));

        List<Vehicle> list = currentUser.getVehicles();
        Vehicle deletedVehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new NoSuchElementException("Vehicle not found"));
        boolean removed = list.removeIf(v -> v.getVehicle_id().equals(vehicleId));

        if (!removed) {
            throw new IllegalStateException("Vehicle does not belong to this user");
        }
        deletedVehicle.setUser(null);
        userRepository.save(currentUser);

        return convertToResponse(currentUser);
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        return List.of();
    }

    @Override
    public UUID findCurrentUser() {
        String loggedInUserEmail = authenticationService.getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(loggedInUserEmail).orElseThrow(() -> new UsernameNotFoundException("User name not found"));
        return user.getUser_id();
    }

    @Override
    public List<VehicleResponseDto> getAllVehiclesOfUser() {
        UUID id = findCurrentUser();
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));

        return user.getVehicles().stream()
                .map(this::convertVehicleToDto)
                .toList();
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found"));
        return convertToResponse(user);
    }

    @Override
    public boolean makeAsFavourite(UUID id) {
        ServiceCenter center = serviceCenterRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Service Center not found"));

        UUID userId = findCurrentUser();
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        List<UUID> favouriteList = user.getFavourites();
        if (favouriteList == null)
            favouriteList = new ArrayList<>();

        favouriteList.add(center.getId());
        user.setFavourites(favouriteList);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<ServiceCenterResponseDto> listOfFavourites() {

        UUID userId = findCurrentUser();
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        List<UUID> favouriteIds = user.getFavourites();
        if (favouriteIds == null || favouriteIds.isEmpty()) {
            return List.of();
        }

        List<ServiceCenter> centres = serviceCenterRepository.findAllById(favouriteIds);  // uses JPA findAllById.[web:27]

        return centres.stream()
                .map(this::convertServiceCenterToResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto updateImage(String image) {

        UUID id = findCurrentUser();
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setImage(image);
        user =userRepository.save(user);
        return convertToResponse(user);
    }


    private UserEntity convertToEntity(UserRequestDto requestDto) {
        return UserEntity.builder()
                .phone(requestDto.getPhone())
                .email(requestDto.getEmail())
                .name(requestDto.getName())
                .role(requestDto.getRole())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
    }

    private VehicleResponseDto convertVehicleToDto(Vehicle vehicle) {
        return VehicleResponseDto.builder()
                .vehicle_id(vehicle.getVehicle_id())
                .vehicle_number(vehicle.getVehicle_number())
                .brand(vehicle.getBrand())
                .fuel_type(vehicle.getFuel_type())
                .model(vehicle.getModel())
                .type(vehicle.getType())
                .build();
    }

    public UserResponseDto convertToResponse(UserEntity newUser) {
        List<VehicleResponseDto> vehicleDtos =
                newUser.getVehicles() == null ? List.of() :
                        newUser.getVehicles().stream()
                                .map(this::convertVehicleToDto)
                                .toList();

        List<BookingResponseDto> bookingDtos =
                newUser.getBookings() == null ? List.of() :
                        newUser.getBookings().stream()
                                .map(this::convertBookingToDto)
                                .toList();

        return UserResponseDto.builder()
                .user_id(newUser.getUser_id())
                .phone(newUser.getPhone())
                .role(newUser.getRole())
                .email(newUser.getEmail())
                .name(newUser.getName())
                .image(newUser.getImage())
                .vehicles(vehicleDtos)
                .bookings(bookingDtos)
                .build();
    }

    private BookingResponseDto convertBookingToDto(Booking booking) {
        UserDto customerDto = UserDto.builder()
                .user_id(booking.getCustomer().getUser_id())
                .email(booking.getCustomer().getEmail())
                .name(booking.getCustomer().getName())
                .build();

        return BookingResponseDto.builder()
                .booking_id(booking.getBooking_id())
                .status(booking.getStatus())
                .customer(customerDto)
                .serviceCenter(convertServiceCenterToDto(booking.getServiceCenter()))
                .vehicle(convertVehicleToDTO(booking.getVehicle()))
                .mobile_number(booking.getMobile_number())
                .name(booking.getName())
                .build();
    }

    private ServiceCenterDto convertServiceCenterToDto(ServiceCenter sc) {
        if (sc == null) return null;

        return ServiceCenterDto.builder()
                .id(sc.getId())
                .garageName(sc.getGarageName())
                .phone(sc.getPhone())
                .build();
    }

    private VehicleDto convertVehicleToDTO(Vehicle v) {
        if (v == null) return null;

        return VehicleDto.builder()
                .vehicle_id(v.getVehicle_id())
                .vehicle_number(v.getVehicle_number())
                .brand(v.getBrand())
                .build();
    }

    private ServiceCenterResponseDto convertServiceCenterToResponseDto(ServiceCenter sc) {
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
                .startTime(sc.getStartTime())
                .endTime(sc.getEndTime())
                .averageRating(sc.getAverageRating())
                .profileUrl(sc.getProfileUrl())
                .coverImgUrl(sc.getCoverImgUrl())
                .gallery(sc.getGallery())
                .socialMedia(sc.getSocialMedia())
                .documents(sc.getDocuments())
                .totalServices(sc.getServices() != null ? sc.getServices().size() : 0)
                .totalBookings(sc.getBookings() != null ? sc.getBookings().size() : 0)
                .plan(sc.getPlan())
                .build();
    }

}
