package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelRoom;

class HotelRoomDoesNotExistException extends RuntimeException {
    HotelRoomDoesNotExistException(String id) {
        super("Hotel Room with id " + id + " does not exist.");
    }
}
