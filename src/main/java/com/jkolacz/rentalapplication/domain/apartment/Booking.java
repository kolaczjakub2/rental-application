package com.jkolacz.rentalapplication.domain.apartment;

import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@SuppressWarnings("PMD.UnusedPrivateField")
public class Booking {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private RentalType rentalType;
    private String rentalPlaceId;
    private String tenantId;
    @ElementCollection
    private List<LocalDate> days;
    private BookingStatus bookingStatus = BookingStatus.OPEN;

    private Booking() {
    }

    Booking(RentalType rentalType, String rentalPlaceId, String tenantId, List<LocalDate> days) {
        this.rentalType = rentalType;
        this.rentalPlaceId = rentalPlaceId;
        this.tenantId = tenantId;
        this.days = days;
    }

    public static Booking apartment(String rentalPlaceId, String tenantId, Period period) {
        List<LocalDate> days = period.asDays();
        return new Booking(RentalType.APARTMENT, rentalPlaceId, tenantId, days);

    }

    public static Booking hotelRoom(String rentalPlaceId, String tenantId, List<LocalDate> days) {
        return new Booking(RentalType.HOTEL_ROOM, rentalPlaceId, tenantId, days);
    }

    public void reject() {
        bookingStatus = BookingStatus.REJECTED;
    }

    public void accept(EventChannel eventChannel) {
        bookingStatus = BookingStatus.ACCEPTED;
        BookingAccepted bookingAccepted = BookingAccepted.create(rentalType, rentalPlaceId, tenantId, days);
        eventChannel.publish(bookingAccepted);

    }

    public UUID getId() {
        return id;
    }

    public String id() {
        return id.toString();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RentalType getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }

    public String getRentalPlaceId() {
        return rentalPlaceId;
    }

    public void setRentalPlaceId(String rentalPlaceId) {
        this.rentalPlaceId = rentalPlaceId;
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

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
