package com.jkolacz.rentalapplication.domain.apartment;

public interface EventChannel {
    void publish(ApartmentBooked apartmentBooked);
}
