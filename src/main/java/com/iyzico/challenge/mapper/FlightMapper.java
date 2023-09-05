package com.iyzico.challenge.mapper;

import com.iyzico.challenge.dto.FlightDTO;
import com.iyzico.challenge.entity.Flight;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface FlightMapper extends BaseDTOMapper<Flight, FlightDTO> {
}
