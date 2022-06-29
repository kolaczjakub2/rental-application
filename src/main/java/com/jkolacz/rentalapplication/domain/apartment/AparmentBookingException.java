package com.jkolacz.rentalapplication.domain.apartment;

import java.time.LocalDate;

public class AparmentBookingException extends RuntimeException {
    AparmentBookingException(LocalDate start, LocalDate end) {
        super("Apartment is not available between " + start + " - " + end);
    }
}
