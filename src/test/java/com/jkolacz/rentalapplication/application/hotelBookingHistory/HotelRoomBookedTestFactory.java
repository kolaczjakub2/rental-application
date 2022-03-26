package com.jkolacz.rentalapplication.application.hotelBookingHistory;

import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomBooked;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class HotelRoomBookedTestFactory {
    public static HotelRoomBooked create(String hotelRoomId, String hotelId, String tenantId, List<LocalDate> days) {
        String id = UUID.randomUUID().toString();
        return new HotelRoomBooked(id, LocalDateTime.now(), hotelRoomId, hotelId, tenantId, days);
    }
}
