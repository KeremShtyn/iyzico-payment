package com.iyzico.challenge.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FlightDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

}
