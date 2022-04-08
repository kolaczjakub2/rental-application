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
    @CollectionTable(name = "APARTMENT_BOOKING", joinColumns = @JoinColumn(name = "APARTMENT_ID"))
    private List<ApartmentBooking> bookings = new ArrayList<>();

    private ApartmentBookingHistory() {
    }

    public ApartmentBookingHistory(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public void add(ApartmentBooking apartmentBooking) {
        bookings.add(apartmentBooking);
    }
}