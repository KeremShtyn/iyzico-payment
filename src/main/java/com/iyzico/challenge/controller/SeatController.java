package com.iyzico.challenge.controller;

import com.iyzico.challenge.api.SeatAPI;
import com.iyzico.challenge.dto.SeatDTO;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.mapper.SeatMapper;
import com.iyzico.challenge.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class SeatController implements SeatAPI {

    private final SeatService seatService;
    private final SeatMapper seatMapper;


    @Autowired
    public SeatController(SeatService seatService, SeatMapper seatMapper) {
        this.seatService = seatService;
        this.seatMapper = seatMapper;
    }

    @Override
    public SeatDTO createOrUpdateSeat(SeatDTO seatDTO) {
        Seat seat = seatMapper.toEntity(seatDTO);
        return seatMapper.toDTO(seatService.createSeat(seat));
    }

    @Override
    public String removeSeat(Long id) {
        return seatService.removeSeat(id);
    }
}
