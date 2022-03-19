package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartment;

public class ApartmentNotFoundException extends RuntimeException {

    public ApartmentNotFoundException(String id) {
        super("Apartment with id " + id + " does not exist");
    }
}
