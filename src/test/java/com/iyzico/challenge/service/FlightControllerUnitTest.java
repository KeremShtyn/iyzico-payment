package com.iyzico.challenge.service;

import com.iyzico.challenge.controller.FlightController;
import com.iyzico.challenge.dto.FlightDTO;
import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class FlightControllerUnitTest {

    @InjectMocks
    private FlightController flightController;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightService flightService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFlight() {
        Flight flight = new Flight();
        flight.setName("Paris");
        flight.setDescription("Tokyo - Paris");

        when(flightRepository.save(flight)).thenReturn(flight);

        Flight createdFlight = flightService.createFlight(flight);

        assertNotNull(createdFlight);
        assertEquals("FlightName", createdFlight.getName());
        assertEquals("FlightDescription", createdFlight.getDescription());
    }
}
