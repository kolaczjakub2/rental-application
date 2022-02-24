package com.jkolacz.rentalapplication.domain.hotelBookingHistory;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
class HotelRoomBooking {
    @Id
    @GeneratedValue
    private UUID id;
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

    public static HotelRoomBooking start(LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        return new HotelRoomBooking(bookingDateTime, tenantId, days);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public List<LocalDate> getDays() {
        return days;
    }

    public void setDays(List<LocalDate> days) {
        this.days = days;
    }
}
