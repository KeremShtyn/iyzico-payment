package com.iyzico.challenge.controller;

import com.iyzico.challenge.api.BookingAPI;
import com.iyzico.challenge.dto.BookingDTO;
import com.iyzico.challenge.mapper.BookingMapper;
import com.iyzico.challenge.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class BookingController implements BookingAPI {


    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingController(BookingService bookingService, BookingMapper bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public BookingDTO createBooking(Long seatId, String passengerName, BigDecimal price, Long flightId) {
        bookingService.bookSeat(seatId, passengerName, price, flightId);
        return bookingMapper.toDTO(bookingService.getBySeatId(seatId));
    }
}
