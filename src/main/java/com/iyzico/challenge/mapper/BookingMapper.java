package com.iyzico.challenge.mapper;

import com.iyzico.challenge.dto.BookingDTO;
import com.iyzico.challenge.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BookingMapper extends BaseDTOMapper<Booking, BookingDTO>{

    @Mapping(target = "flightName", source = "flight.name")
    @Mapping(target = "flightId", source = "flight.id")
    @Mapping(target = "seatNumber", source = "seat.seatNumber")
    @Mapping(target = "seatId", source = "seat.id")
    BookingDTO toDTO(Booking entity);

    @Mapping(target = "flight.name", source = "flightName")
    @Mapping(target = "flight.id", source = "flightId")
    @Mapping(target = "seat.seatNumber", source = "seatNumber")
    @Mapping(target = "seat.id", source = "seatId")
    Booking toEntity(BookingDTO domain);
}
