package com.iyzico.challenge.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name= "Seat", indexes = {@Index(columnList = "id", name = "seat_id_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Entity
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNumber;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
