package com.garagediary.garagediary.service;

import com.garagediary.garagediary.dto.BillRequestDto;
import com.garagediary.garagediary.dto.BillResponseDto;

import java.util.UUID;

public interface BillService {
    BillResponseDto generateBill(Long bookingId);
    BillResponseDto getBillDetails(UUID billID);
    double calculateSubTotal(BillRequestDto requestDto);
    BillResponseDto applyDiscount(Double amount);
}
