package com.garagediary.garagediary.service.impl;

import com.garagediary.garagediary.Repository.BillRepository;
import com.garagediary.garagediary.Repository.BookingRepository;
import com.garagediary.garagediary.dto.BillRequestDto;
import com.garagediary.garagediary.dto.BillResponseDto;
import com.garagediary.garagediary.entity.Bill;
import com.garagediary.garagediary.entity.Booking;
import com.garagediary.garagediary.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BookingRepository bookingRepository;

    private static final double TAX_RATE = 0.18;     // 18%
    private static final double DISCOUNT_RATE = 0.10; // 10%

    @Override
    public BillResponseDto generateBill(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        double subtotal = calculateSubTotal(
                BillRequestDto.builder()
                        .booking(booking)
                        .build()
        );

        double discount = subtotal * DISCOUNT_RATE;
        double tax = (subtotal - discount) * TAX_RATE;
        double totalAmount = subtotal - discount + tax;

        Bill bill = new Bill();
        bill.setBooking(booking);
        bill.setService_center_id(booking.getServiceCenter().getId());
        bill.setSubtotal(subtotal);
        bill.setDiscount(discount);
        bill.setTax(tax);
        bill.setTotal_amount(totalAmount);
        bill.setGenerated_date(LocalDateTime.now());

        Bill savedBill = billRepository.save(bill);

        return mapToResponseDto(savedBill);
    }

    @Override
    public BillResponseDto getBillDetails(UUID billID) {

        Bill bill = billRepository.findById(billID)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        return mapToResponseDto(bill);
    }

    @Override
    public double calculateSubTotal(BillRequestDto requestDto) {

        Booking booking = requestDto.getBooking();

        if (booking.getService() == null) {
            throw new RuntimeException("No service linked with booking");
        }

        return booking.getService().getPrice();
    }

    @Override
    public BillResponseDto applyDiscount(Double amount) {

        double discount = amount * DISCOUNT_RATE;
        double finalAmount = amount - discount;

        return BillResponseDto.builder()
                .subtotal(amount)
                .discount(discount)
                .total_amount(finalAmount)
                .build();
    }

    private BillResponseDto mapToResponseDto(Bill bill) {

        return BillResponseDto.builder()
                .bill_id(bill.getBill_id())
                .booking(bill.getBooking())
                .service_center_id(bill.getService_center_id())
                .subtotal(bill.getSubtotal())
                .discount(bill.getDiscount())
                .tax(bill.getTax())
                .total_amount(bill.getTotal_amount())
                .generated_date(bill.getGenerated_date())
                .build();
    }
}
