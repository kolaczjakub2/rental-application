package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APARTMENT_BOOKING_HISTORY")
public class ApartmentBookingHistory {
    @Id
    private String apartmentId;
    @ElementCollection
    private List<ApartmentBooking> bookings = new ArrayList<>();

    public ApartmentBookingHistory() {
    }

    public ApartmentBookingHistory(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public void add(ApartmentBooking apartmentBooking) {
        bookings.add(apartmentBooking);
    }


    private String getApartmentId() {
        return apartmentId;
    }

    private void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    private List<ApartmentBooking> getBookings() {
        return bookings;
    }

    private void setBookings(List<ApartmentBooking> bookings) {
        this.bookings = bookings;
    }
}
