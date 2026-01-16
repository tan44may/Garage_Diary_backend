package com.garagediary.garagediary.Controller;

import com.garagediary.garagediary.dto.BookingRequestDto;
import com.garagediary.garagediary.dto.BookingResponseDto;
import com.garagediary.garagediary.dto.UpdateStatusRequest;
import com.garagediary.garagediary.entity.enums.Status;
import com.garagediary.garagediary.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(
            @RequestBody BookingRequestDto dto
    ) {

        return ResponseEntity.ok(bookingService.createBooking(dto));
    }


    @GetMapping("/service-center/{serviceCenterId}")
    public ResponseEntity<List<BookingResponseDto>> getBookingsByServiceCenter(
            @PathVariable UUID serviceCenterId
    ) {
        return ResponseEntity.ok(
                bookingService.getBookingsByServiceCenter(serviceCenterId)
        );
    }

    @GetMapping("/service-center/{serviceCenterId}/status/{status}")
    public ResponseEntity<List<BookingResponseDto>> getBookingsByStatus(
            @PathVariable UUID serviceCenterId,
            @PathVariable Status status
    ) {
        return ResponseEntity.ok(
                bookingService.getBookingsByStatus(serviceCenterId, status)
        );
    }


    @PutMapping("/status/{bookingId}")
    public ResponseEntity<BookingResponseDto> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam Status status
    ) {
        return ResponseEntity.ok(
                bookingService.updateBookingStatus(bookingId, status)
        );
    }

    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<Void> cancelBooking(
            @PathVariable Long bookingId
    ) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

}