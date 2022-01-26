package com.jkolacz.rentalapplication.domain.apartment.apartmentbookinghistory;

public class ApartmentBookingHistory {
    private final String apartmentId;

    public ApartmentBookingHistory(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public void add(ApartmentBooking start) {

    }

    public String getApartmentId() {
        return apartmentId;
    }


}
