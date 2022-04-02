package com.jkolacz.rentalapplication.domain.hotelBookingHistory;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
class HotelRoomBooking {
    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime bookingDateTime;
    private String tenantId;

    @ElementCollection
    private List<LocalDate> days;

    private HotelRoomBooking() {}

    HotelRoomBooking(LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        this.bookingDateTime = bookingDateTime;
        this.tenantId = tenantId;
        this.days = days;
    }
}
