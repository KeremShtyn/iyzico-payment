package com.iyzico.challenge.controller;


import com.iyzico.challenge.api.FlightAPI;
import com.iyzico.challenge.dto.FlightDTO;
import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.mapper.FlightMapper;
import com.iyzico.challenge.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class FlightController implements FlightAPI {

    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightController(FlightService flightService, FlightMapper flightMapper) {
        this.flightService = flightService;
        this.flightMapper = flightMapper;
    }

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        Flight flight = flightMapper.toEntity(flightDTO);
        return flightMapper.toDTO(flightService.createFlight(flight));
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        return flightMapper.toDTO(flightService.findById(id));
    }

    @Override
    public List<FlightDTO> getAll() {
        return flightMapper.toListDTO(flightService.findAllFlights());
    }

    @Override
    public String removeFlight(Long id) {
        return flightService.removeFlight(id);
    }
}
