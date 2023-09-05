package com.iyzico.challenge.mapper;

import com.iyzico.challenge.dto.BookingDTO;
import com.iyzico.challenge.dto.SeatDTO;
import com.iyzico.challenge.entity.Booking;
import com.iyzico.challenge.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatMapper extends BaseDTOMapper<Seat, SeatDTO>{

    @Mapping(target = "flightName", source = "flight.name")
    @Mapping(target = "flightId", source = "flight.id")
    BookingDTO toDTO(Booking entity);

    @Mapping(target = "flight.name", source = "flightName")
    @Mapping(target = "flight.id", source = "flightId")
    Booking toEntity(BookingDTO dto);
}
