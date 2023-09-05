package com.iyzico.challenge.api;

import com.iyzico.challenge.dto.FlightDTO;
import com.iyzico.challenge.entity.Flight;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.iyzico.challenge.modal.constants.IyzicoApiEndpoint.FLIGHT_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(FLIGHT_PATH)
public interface FlightAPI {

    @PostMapping
    public FlightDTO createFlight(@RequestBody FlightDTO flight);

    @GetMapping("{id}")
    public FlightDTO getFlightById(@RequestParam Long id);

    @GetMapping()
    public List<FlightDTO> getAll();

    @DeleteMapping("/{id}")
    public String removeFlight(@PathVariable Long id);


}
