package com.jkolacz.rentalapplication.query.hotelRoom;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "HOTEL_ROOM")
public class HotelRoomReadModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String hotelId;
    private int number;

    @ElementCollection
    @CollectionTable(name = "HOTEL_ROOM_SPACE", joinColumns = @JoinColumn(name = "HOTEL_ROOM_ID"))
    private List<SpaceReadModel> spaces;

    private String description;

    private HotelRoomReadModel() {}

    public String getId() {
        return id.toString();
    }

    public String getHotelId() {
        return hotelId;
    }

    public int getNumber() {
        return number;
    }

    public List<SpaceReadModel> getSpaces() {
        return spaces;
    }

    public String getDescription() {
        return description;
    }
}