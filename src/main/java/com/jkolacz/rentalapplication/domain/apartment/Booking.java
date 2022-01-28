package com.jkolacz.rentalapplication.domain.apartment;

import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Booking {
    private final RentalType rentalType;
    @Id
    @GeneratedValue
    private String id;
    private final String rentalPlaceId;
    private final String tenantId;
    private final List<LocalDate> days;
    private BookingStatus bookingStatus = BookingStatus.OPEN;

    public Booking(RentalType rentalType, String rentalPlaceId, String tenantId, List<LocalDate> days) {

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
}
