package com.jkolacz.rentalapplication.domain.hotel;

public class HotelFactory {
    @SuppressWarnings("checkstyle:ParameterNumber")
    public Hotel create(String name, String street, String buildingNumber, String postalCode, String city, String country) {
        Address address = new Address(street, postalCode, buildingNumber, city, country);
        return new Hotel(name, address);
    }
}
