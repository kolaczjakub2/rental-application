package com.jkolacz.rentalapplication.application.apartment;

import com.jkolacz.rentalapplication.domain.apartment.NewApartmentBookingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ApartmentBookingDto {
    private final String apartmentId;
    private final String tenantId;
    private final LocalDate start;
    private final LocalDate end;

    NewApartmentBookingDto asNewApartmentBookingDto() {
        return new NewApartmentBookingDto(apartmentId, tenantId, start, end);
    }
}
