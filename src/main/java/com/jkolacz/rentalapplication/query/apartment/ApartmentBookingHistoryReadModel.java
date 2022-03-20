package com.jkolacz.rentalapplication.query.apartment;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "APARTMENT_BOOKING_HISTORY")
public class ApartmentBookingHistoryReadModel {
    @Id
    private final UUID apartmentId;

    @ElementCollection
    private List<ApartmentBookingReadModel> bookings = new ArrayList<>();
    public ApartmentBookingHistoryReadModel(UUID apartmentId) {
        this.apartmentId = apartmentId;
    }

    public UUID getApartmentId() {
        return apartmentId;
    }

    public List<ApartmentBookingReadModel> getBookings() {
        return bookings;
    }
}
