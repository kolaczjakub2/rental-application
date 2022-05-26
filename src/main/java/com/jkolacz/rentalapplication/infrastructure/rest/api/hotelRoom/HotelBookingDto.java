package com.jkolacz.rentalapplication.infrastructure.rest.api.hotelRoom;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class HotelBookingDto {
    private final String tenantId;
    private final List<LocalDate> days;
}
