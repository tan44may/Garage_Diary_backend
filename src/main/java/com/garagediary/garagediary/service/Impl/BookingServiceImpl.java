package com.garagediary.garagediary.service.impl;

import com.garagediary.garagediary.Repository.BookingRepository;
import com.garagediary.garagediary.Repository.ServiceCenterRepository;
import com.garagediary.garagediary.Repository.UserRepository;
import com.garagediary.garagediary.dto.*;
import com.garagediary.garagediary.entity.Booking;
import com.garagediary.garagediary.entity.ServiceCenter;
import com.garagediary.garagediary.entity.UserEntity;
import com.garagediary.garagediary.entity.Vehicle;
import com.garagediary.garagediary.entity.enums.Status;
import com.garagediary.garagediary.service.BookingService;
import com.garagediary.garagediary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final ServiceCenterRepository serviceCenterRepository;
    private final BookingRepository bookingRepository;

    @Override
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {

        Booking newBooking = new Booking();
        newBooking.setStatus(Status.PENDING);
        UUID id = userService.findCurrentUser();
        UserEntity customer = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User not found"));
        ServiceCenter center = serviceCenterRepository.findById(bookingRequestDto.getServiceCenter_id()).orElseThrow(()-> new NoSuchElementException("Service center not found"));
        newBooking.setCustomer(customer);
        newBooking.setName(bookingRequestDto.getName());
        newBooking.setServiceCenter(center);
        List<Vehicle> vehicleList = customer.getVehicles();
        Vehicle currentVehicle = null;
        for(Vehicle v : vehicleList)
        {
            if(v.getVehicle_id().toString().equals(bookingRequestDto.getVehicle_id()))
            {
                currentVehicle= v;
                break;
            }
        }
        if(currentVehicle == null)
        {
            throw  new NoSuchElementException("Vehicle not fount with given vehicle id ");
        }
        newBooking.setVehicle(currentVehicle);
        newBooking.setMobile_number(bookingRequestDto.getMobile_number());
        newBooking = bookingRepository.save(newBooking);
        List<Booking> list =  customer.getBookings();

        if(list == null)
        {
            list= new ArrayList<>();
        }
        list.add(newBooking);
        customer.setBookings(list);
        userRepository.save(customer);

        List<Booking> serviceList = center.getBookings();

        if(serviceList == null)
        {
            serviceList= new ArrayList<>();
        }
        serviceList.add(newBooking);
        center.setBookings(serviceList);
        serviceCenterRepository.save(center);

        return convertToResponse(newBooking);
    }

    private BookingResponseDto convertToResponse(Booking booking) {
        return BookingResponseDto.builder()
                .booking_id(booking.getBooking_id())
                .status(booking.getStatus())
                .mobile_number(booking.getMobile_number())
                .name(booking.getName())
                .customer(UserDto.builder()
                        .user_id(booking.getCustomer().getUser_id())
                        .email(booking.getCustomer().getEmail())
                        .name(booking.getCustomer().getName())
                        .build())
                .serviceCenter(ServiceCenterDto.builder()
                        .id(booking.getServiceCenter().getId())
                        .garageName(booking.getServiceCenter().getGarageName())
                        .phone(booking.getServiceCenter().getPhone())
                        .build())
                .vehicle(VehicleDto.builder()
                        .vehicle_id(booking.getVehicle().getVehicle_id())
                        .vehicle_number(booking.getVehicle().getVehicle_number())
                        .brand(booking.getVehicle().getBrand())
                        .build())
                .build();
    }


    @Override
    public BookingResponseDto getBookingDetails(UUID bookingId) {
        return null;
    }

    @Override
    public BookingResponseDto updateBookingStatus(UUID bookingId) {
        return null;
    }

    @Override
    public BookingResponseDto cancelBooking(UUID bookingId) {
        return null;
    }

    @Override
    public List<BookingResponseDto> getAllBookingsByUser(UUID userId) {
        return List.of();
    }

    @Override
    public BookingResponseDto getBookingById(UUID bookingId) {
        return null;
    }
}
