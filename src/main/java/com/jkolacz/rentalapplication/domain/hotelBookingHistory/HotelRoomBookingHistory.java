package com.jkolacz.rentalapplication.domain.hotelBookingHistory;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
class HotelRoomBookingHistory {
    @Id
    private String hotelRoomId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<HotelRoomBooking> bookings = new ArrayList<>();

    private HotelRoomBookingHistory() {}

    HotelRoomBookingHistory(String hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }

    boolean hasIdEqualTo(String hotelRoomId) {
        return this.hotelRoomId.equals(hotelRoomId);
    }

    void add(LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        bookings.add(new HotelRoomBooking(bookingDateTime, tenantId, days));
    }

    public String getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(String hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }

    public List<HotelRoomBooking> getBookings() {
        return bookings;
    }

    public void setBookings(List<HotelRoomBooking> bookings) {
        this.bookings = bookings;
    }
}
