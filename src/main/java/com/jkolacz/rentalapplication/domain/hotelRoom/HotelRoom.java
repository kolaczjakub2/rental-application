package com.jkolacz.rentalapplication.domain.hotelRoom;

import com.jkolacz.rentalapplication.domain.apartment.Booking;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "HOTEL_ROOM")
public class HotelRoom {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private UUID hotelRoomId;
    private String hotelId;
    private Integer number;
    private String description;
    @ElementCollection
    @CollectionTable(name = "HOTEL_ROOM_SPACE", joinColumns = @JoinColumn(name = "HOTEL_ROOM_ID"))
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
        HotelRoomBooked hotelRoomBooked = HotelRoomBooked.create(id(),hotelId,tenantId, days);
        eventChannel.publish(hotelRoomBooked);
        return Booking.hotelRoom(id(), tenantId, days);
    }

    public String id() {
        if (id == null) {
            return null;
        }

        return id.toString();
    }

}
