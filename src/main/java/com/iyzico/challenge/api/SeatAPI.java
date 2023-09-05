package com.iyzico.challenge.api;


import com.iyzico.challenge.dto.FlightDTO;
import com.iyzico.challenge.dto.SeatDTO;
import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.mapper.SeatMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.iyzico.challenge.modal.constants.IyzicoApiEndpoint.FLIGHT_PATH;
import static com.iyzico.challenge.modal.constants.IyzicoApiEndpoint.SEAT_PATH;


@CrossOrigin(origins = "*")
@RequestMapping(SEAT_PATH)
public interface SeatAPI {

    @PostMapping
    public SeatDTO createOrUpdateSeat(@RequestBody SeatDTO seatDTO);

    @DeleteMapping("/{id}")
    public String removeSeat(@PathVariable Long id);
}
