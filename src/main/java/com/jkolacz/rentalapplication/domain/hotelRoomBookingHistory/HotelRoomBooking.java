package com.jkolacz.rentalapplication.domain.hotelRoomBookingHistory;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class HotelRoomBooking {
    private final LocalDateTime bookingDateTime;
    private final String tenantId;
    private final List<LocalDate> days;


    public HotelRoomBooking(LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        this.bookingDateTime = bookingDateTime;
        this.tenantId = tenantId;
        this.days = days;
    }

    public static HotelRoomBooking start(LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        return new HotelRoomBooking(bookingDateTime, tenantId, days);
    }

}
