package com.iyzico.challenge.service;

import com.iyzico.challenge.entity.Booking;
import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.error.ErrorCodes;
import com.iyzico.challenge.exception.IyzicoException;
import com.iyzico.challenge.repository.BookingRepository;
import com.iyzico.challenge.repository.FlightRepository;
import com.iyzico.challenge.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class BookingService {
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final PaymentServiceClients paymentServiceClients;
    private final EntityManager entityManager;
    private final FlightRepository flightRepository;


    public BookingService(BookingRepository bookingRepository, SeatRepository seatRepository, PaymentServiceClients paymentServiceClients, EntityManager entityManager, FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.paymentServiceClients = paymentServiceClients;
        this.entityManager = entityManager;
        this.flightRepository = flightRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    @Transactional
    public CompletableFuture<Boolean> bookSeat(Long seatId, String passengerName, BigDecimal price, Long flightId) {
        Seat seat = seatRepository.findById(seatId).orElse(null);
        Flight flight = flightRepository.findById(flightId).orElse(null);

        if (seat != null && seat.isAvailable() && flight != null) {
            synchronized (seat) {
                if (seat.isAvailable()) {
                    seat.setAvailable(false);
                    seatRepository.save(seat);

                    return paymentServiceClients.call(price)
                            .thenApply(result -> {
                                if (result.equals("success")) {
                                    Booking booking = new Booking();
                                    booking.setSeat(seat);
                                    booking.setPassengerName(passengerName);
                                    booking.setPrice(price);
                                    entityManager.persist(booking);
                                    return true;
                                } else {
                                    seat.setAvailable(true);
                                    seatRepository.save(seat);
                                    return false;
                                }
                            });

                }
            }
        }
        return CompletableFuture.completedFuture(false);
    }

    public Booking getBySeatId(Long seatId) {
        return bookingRepository.findBySeatId(seatId).orElseThrow(() -> new IyzicoException(ErrorCodes.DATA_NOT_FOUND));
    }

}
