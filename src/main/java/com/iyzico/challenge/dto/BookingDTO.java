package com.iyzico.challenge.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookingDTO {

    private Long id;
    private Long flightId;
    private String flightName;
    private Long seatId;
    private String seatNumber;
    private BigDecimal totalPrice;
    private String passengerName;

}
