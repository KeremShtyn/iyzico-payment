package com.iyzico.challenge.service;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.error.ErrorCodes;
import com.iyzico.challenge.exception.IyzicoException;
import com.iyzico.challenge.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
@Slf4j
public class SeatService {

    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }


    private Seat saveOrUpdate(Seat seat) {
        return seatRepository.save(seat);
    }

    public Seat createSeat(Seat seat){
        this.validateFlight(seat);
        return saveOrUpdate(seat);
    }
    public String removeSeat(Long id) {
        Seat existingFlight = seatRepository.findById(id).orElse(null);

        if (existingFlight != null) {
            seatRepository.delete(existingFlight);
            return ("Seat removed");
        }

        return ("Seat does not exist");
    }

    private void validateFlight(Seat seat){

        if (Objects.isNull(seat)) {
            throw new IyzicoException(ErrorCodes.SEAT_DATA_CAN_NOT_BE_EMPTY);
        }

        if (StringUtils.isEmpty(seat.getFlight())) {
            throw new IyzicoException(ErrorCodes.FLIGHT_NAME_CAN_NOT_BE_EMPTY);
        }

        if (StringUtils.isEmpty(seat.getSeatNumber())) {
            throw new IyzicoException(ErrorCodes.SEAT_NUMBER_CAN_NOT_BE_EMPTY);
        }
    }
}
