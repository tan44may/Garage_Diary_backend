package com.garagediary.garagediary.Controller;

import com.garagediary.garagediary.Repository.BookingRepository;
import com.garagediary.garagediary.dto.BookingRequestDto;
import com.garagediary.garagediary.dto.BookingResponseDto;
import com.garagediary.garagediary.dto.UpdateStatusRequest;
import com.garagediary.garagediary.entity.enums.Status;
import com.garagediary.garagediary.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user/booking")
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto requestDto)
    {
        return ResponseEntity.ok(bookingService.createBooking(requestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getBookingDetails(@PathVariable UUID id)
    {
        return ResponseEntity.ok(bookingService.getBookingDetails(id));
    }
    @PutMapping("update-status/{id}")
    public ResponseEntity<BookingResponseDto> updateBookingStatus(@PathVariable UUID id , @RequestBody UpdateStatusRequest request)
    {
        return ResponseEntity.ok(bookingService.updateBookingStatus(id,request.getStatus()));
    }
    @PutMapping("cancel/{id}")
    public ResponseEntity<BookingResponseDto> cancelBooking(@PathVariable UUID id )
    {
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }

    @GetMapping("/all-bookings/{id}")
    public ResponseEntity<List<BookingResponseDto>> getAllBookings(@PathVariable UUID id)
    {
        return ResponseEntity.ok(bookingService.getAllBookingsByUser(id));
    }

}
