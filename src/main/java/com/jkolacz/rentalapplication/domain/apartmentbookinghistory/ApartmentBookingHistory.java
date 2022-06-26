package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APARTMENT_BOOKING_HISTORY")
@SuppressWarnings("PMD.UnusedPrivateField")
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

    private void add(ApartmentBooking apartmentBooking) {
        bookings.add(apartmentBooking);
    }

    public void addBookingStart(LocalDateTime bookingDateTIme, String ownerId, String tenantId, BookingPeriod bookingPeriod) {
        add(ApartmentBooking.start(bookingDateTIme, ownerId, tenantId, bookingPeriod));
    }
}
