package com.jkolacz.rentalapplication.domain.apartment;

import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
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
        List<LocalDate> days= period.asDays();
        return new Booking(RentalType.APARTMENT,rentalPlaceId,tenantId,days);

    }

    public static Booking hotelRoom(String rentalPlaceId, String tenantId, List<LocalDate> days) {
        return new Booking(RentalType.HOTEL_ROOM,rentalPlaceId,tenantId,days);
    }

    public void reject() {
        bookingStatus = BookingStatus.REJECTED;
    }

    public void accept(EventChannel eventChannel) {
        bookingStatus = BookingStatus.ACCEPTED;
        BookingAccepted bookingAccepted =  BookingAccepted.create(rentalType,rentalPlaceId,tenantId,days);
        eventChannel.publish(bookingAccepted);

    }
    
    private UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private RentalType getRentalType() {
        return rentalType;
    }

    private void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }

    private String getRentalPlaceId() {
        return rentalPlaceId;
    }

    private void setRentalPlaceId(String rentalPlaceId) {
        this.rentalPlaceId = rentalPlaceId;
    }

    private String getTenantId() {
        return tenantId;
    }

    private void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    private List<LocalDate> getDays() {
        return days;
    }

    private void setDays(List<LocalDate> days) {
        this.days = days;
    }

    private BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    private void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
