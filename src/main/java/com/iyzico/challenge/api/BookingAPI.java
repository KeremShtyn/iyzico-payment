package com.iyzico.challenge.api;

import com.iyzico.challenge.dto.BookingDTO;
import com.iyzico.challenge.entity.Booking;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.iyzico.challenge.modal.constants.IyzicoApiEndpoint.BOOKING_PATH;


@CrossOrigin(origins = "*")
@RequestMapping(BOOKING_PATH)
public interface BookingAPI {

    @PostMapping("/create")
    public BookingDTO createBooking(@RequestParam Long seatId, @RequestParam  String passengerName, @RequestParam BigDecimal price, @RequestParam Long flightId);

}
