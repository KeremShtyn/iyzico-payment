package com.iyzico.challenge.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SeatDTO {

    private Long id;
    private String seatNumber;
    private boolean isAvailable;
    private BigDecimal price;
    private Long flightId;
    private String flightName;

}
