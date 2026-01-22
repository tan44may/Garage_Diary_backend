package com.garagediary.garagediary.service.impl;

import com.garagediary.garagediary.Repository.*;
import com.garagediary.garagediary.dto.*;
import com.garagediary.garagediary.entity.*;
import com.garagediary.garagediary.entity.enums.Status;
import com.garagediary.garagediary.service.BookingService;
import com.garagediary.garagediary.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ServiceCenterRepository serviceCenterRepository;
    private final ServiceOfferedRepository serviceOfferedRepository;
    private final VehicleRepository vehicleRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public BookingResponseDto createBooking(BookingRequestDto dto) {

        ServiceCenter serviceCenter = serviceCenterRepository.findById(dto.getServiceCenterId())
                .orElseThrow(() -> new NoSuchElementException("Service Center not found"));

        ServiceOffered service = serviceOfferedRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new NoSuchElementException("Service not found"));

        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new NoSuchElementException("Vehicle not found"));

        UUID id = userService.findCurrentUser();
        UserEntity customer = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setServiceCenter(serviceCenter);
        booking.setService(service);
        booking.setVehicle(vehicle);

        booking.setBookingDate(LocalDate.now());
        booking.setBookingTime(LocalTime.now());

        booking.setMobileNumber(dto.getMobileNumber());
        booking.setCustomerName(dto.getCustomerName());

        booking.setStatus(Status.PENDING);

        Booking saved = bookingRepository.save(booking);
        List<Booking> list = serviceCenter.getBookings();
        if(list.isEmpty())
        {
            list = new ArrayList<>();
        }
        list.add(saved);
        serviceCenter.setBookings(list);
        serviceCenterRepository.save(serviceCenter);

        List<Booking> custList = customer.getBookings();
        if(custList.isEmpty())
        {
            custList = new ArrayList<>();
        }
        custList.add(saved);
        customer.setBookings(custList);
        userRepository.save(customer);

        return mapToDto(saved);
    }

    @Override
    public List<BookingResponseDto> getBookingsByServiceCenter(UUID serviceCenterId) {
        return bookingRepository.findByServiceCenterId(serviceCenterId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public List<BookingResponseDto> getBookingsByStatus(UUID serviceCenterId, Status status) {
        return bookingRepository.findByServiceCenterIdAndStatus(serviceCenterId, status)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public BookingResponseDto updateBookingStatus(Long bookingId, Status status) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NoSuchElementException("Booking not found"));

        booking.setStatus(status);
        return mapToDto(bookingRepository.save(booking));
    }

    @Override
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NoSuchElementException("Booking not found"));

        booking.setStatus(Status.CANCELLED);
        bookingRepository.save(booking);
    }

    private BookingResponseDto mapToDto(Booking booking) {
        return BookingResponseDto.builder()
                .bookingId(booking.getBookingId())
                .status(booking.getStatus())
                .customerName(booking.getCustomerName())
                .serviceCenterName(booking.getServiceCenter().getGarageName())
                .serviceName(booking.getService().getServiceName())
                .bookingDate(booking.getBookingDate())
                .bookingTime(booking.getBookingTime())
                .mobileNumber(booking.getMobileNumber())
                .build();
    }
}
