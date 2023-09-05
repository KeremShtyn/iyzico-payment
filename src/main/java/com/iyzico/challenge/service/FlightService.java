package com.iyzico.challenge.service;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.error.ErrorCodes;
import com.iyzico.challenge.exception.IyzicoException;
import com.iyzico.challenge.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findAllFlights(){
        return flightRepository.findAll();
    }

    public Flight findById(Long id){
        return flightRepository.findById(id).orElseThrow(() -> new IyzicoException(ErrorCodes.DATA_NOT_FOUND));
    }

    private Flight saveOrUpdate(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight createFlight(Flight flight){
        this.validateFlight(flight);
        return saveOrUpdate(flight);
    }
    public String removeFlight(Long id) {
        Flight existingFlight = flightRepository.findById(id).orElse(null);

        if (existingFlight != null) {
            flightRepository.delete(existingFlight);
            return ("Flight removed");
        }

        return ("Flight does not exist");
    }

    private void validateFlight(Flight flight){

        if (Objects.isNull(flight)) {
            throw new IyzicoException(ErrorCodes.FLIGHT_DATA_CAN_NOT_BE_EMPTY);
        }

        if (StringUtils.isEmpty(flight.getName())) {
            throw new IyzicoException(ErrorCodes.FLIGHT_NAME_CAN_NOT_BE_EMPTY);
        }

        if (StringUtils.isEmpty(flight.getSeats())) {
            throw new IyzicoException(ErrorCodes.FLIGHT_SEAT_CAN_NOT_BE_EMPTY);
        }
        if (StringUtils.isEmpty(flight.getDescription())) {
            throw new IyzicoException(ErrorCodes.FLIGHT_DESCRIPTION_CAN_NOT_BE_EMPTY);
        }
    }
}
