package com.garagediary.garagediary.service;

import com.garagediary.garagediary.dto.BookingRequestDto;
import com.garagediary.garagediary.dto.BookingResponseDto;
import com.garagediary.garagediary.entity.enums.Status;

import java.util.List;
import java.util.UUID;

public interface BookingService {
     BookingResponseDto createBooking(BookingRequestDto bookingRequestDto);
     BookingResponseDto getBookingDetails(UUID bookingId);
     BookingResponseDto updateBookingStatus(UUID bookingId, Status status);
     BookingResponseDto cancelBooking(UUID bookingId);
     List<BookingResponseDto> getAllBookingsByUser(UUID userId);

}
