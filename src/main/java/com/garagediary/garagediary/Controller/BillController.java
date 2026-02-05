package com.garagediary.garagediary.Controller;

import com.garagediary.garagediary.dto.BillRequestDto;
import com.garagediary.garagediary.dto.BillResponseDto;
import com.garagediary.garagediary.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping("/generate/{bookingId}")
    public ResponseEntity<BillResponseDto> generateBill(
            @PathVariable long bookingId) {

        BillResponseDto response = billService.generateBill(bookingId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<BillResponseDto> getBillDetails(
            @PathVariable UUID billId) {

        BillResponseDto response = billService.getBillDetails(billId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/apply-discount")
    public ResponseEntity<BillResponseDto> applyDiscount(
            @RequestParam Double amount) {

        BillResponseDto response = billService.applyDiscount(amount);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/calculate-subtotal")
    public ResponseEntity<Double> calculateSubTotal(
            @RequestBody BillRequestDto requestDto) {

        double subtotal = billService.calculateSubTotal(requestDto);
        return ResponseEntity.ok(subtotal);
    }
}

