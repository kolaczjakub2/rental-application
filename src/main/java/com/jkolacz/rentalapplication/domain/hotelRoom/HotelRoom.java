package com.jkolacz.rentalapplication.domain.hotelRoom;

import com.jkolacz.rentalapplication.domain.apartment.Booking;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="HOTEL_ROOM")
public class HotelRoom {
    @Id
    @GeneratedValue
    private UUID hotelRoomId;
    private  String hotelId;
    private  Integer number;
    private  String description;
    @ElementCollection
    private List<Space> spaces;

    public HotelRoom() {
    }

    HotelRoom(String hotelId, Integer number, String description, List<Space> spaces) {
        this.hotelId = hotelId;
        this.number = number;
        this.description = description;
        this.spaces = spaces;
    }

    public Booking book(String tenantId, List<LocalDate> days, EventChannel eventChannel) {
        HotelRoomBooked hotelRoomBooked = HotelRoomBooked.create(hotelRoomId.toString(), tenantId, hotelId, days);
        eventChannel.publish(hotelRoomBooked);
        return Booking.hotelRoom(hotelRoomId.toString(),tenantId,days);
    }

    public UUID getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(UUID hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public String id() {
        return getHotelRoomId().toString();
    }
}
