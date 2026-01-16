package com.garagediary.garagediary.service;

import com.garagediary.garagediary.dto.BookingRequestDto;
import com.garagediary.garagediary.dto.BookingResponseDto;
import com.garagediary.garagediary.entity.enums.Status;

import java.util.List;
import java.util.UUID;

public interface BookingService {

     BookingResponseDto createBooking(BookingRequestDto dto);

     List<BookingResponseDto> getBookingsByServiceCenter(UUID serviceCenterId);

     List<BookingResponseDto> getBookingsByStatus(UUID serviceCenterId, Status status);

     BookingResponseDto updateBookingStatus(Long bookingId, Status status);

     void cancelBooking(Long bookingId);
}

