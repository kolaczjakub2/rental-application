package com.jkolacz.rentalapplication.domain.hotelRoom;

import com.jkolacz.rentalapplication.domain.apartment.Booking;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

//@Entity
@Table(name="HOTEL_ROOM")
public class HotelRoom {
    @Id
    @GeneratedValue
    private String hotelRoomId;
    private final String hotelId;
    private final Integer number;
    private final String description;
    @OneToMany
    private final List<Space> rooms;

    HotelRoom(String hotelId, Integer number, String description, List<Space> rooms) {
        this.hotelId = hotelId;
        this.number = number;
        this.description = description;
        this.rooms = rooms;
    }

    public Booking book(String tenantId, List<LocalDate> days, EventChannel eventChannel) {
        HotelRoomBooked hotelRoomBooked = HotelRoomBooked.create(hotelRoomId, tenantId, hotelId, days);
        eventChannel.publish(hotelRoomBooked);
        return Booking.hotelRoom(hotelRoomId,tenantId,days);
    }
}
