package com.iyzico.challenge.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Table(name= "Flight", indexes = {@Index(columnList = "id", name = "flight_id_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    @OneToMany(mappedBy = "flight", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Seat> seats;
    @OneToMany(mappedBy = "flight", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Booking> bookings;
}
