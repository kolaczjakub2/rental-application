package com.jkolacz.rentalapplication.domain.hotelRoom;

import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class HotelRoom {
    @Id
    @GeneratedValue
    private String id;
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

    public void book(String tenantId, List<LocalDate> days, EventChannel eventChannel) {
        HotelRoomBooked hotelRoomBooked = HotelRoomBooked.create(id, tenantId, hotelId, days);
        eventChannel.publish(hotelRoomBooked);
    }
}
