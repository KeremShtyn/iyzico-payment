package com.iyzico.challenge.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name= "Booking", indexes = {@Index(columnList = "id", name = "booking_id_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Entity(name = "Booking")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @OneToOne(mappedBy = "booking")
    private Seat seat;

    private BigDecimal price;
    private String passengerName;

}
