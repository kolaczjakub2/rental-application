package com.jkolacz.rentalapplication.query.hotelRoom;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

//@Entity
@Table(name = "HOTEL_ROOM")
public class HotelRoomReadModel {

    @Id
    @GeneratedValue
    private UUID id;

    private String hotelRoomId;
    private final String hotelId;
    private final Integer number;
    private final String description;

    @OneToMany
    private final List<SpaceReadModel> spaces;

    public HotelRoomReadModel(String hotelRoomId, String hotelId, Integer number, String description, List<SpaceReadModel> spaces) {
        this.hotelRoomId = hotelRoomId;
        this.hotelId = hotelId;
        this.number = number;
        this.description = description;
        this.spaces = spaces;
    }

    public String getHotelRoomId() {
        return hotelRoomId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public Integer getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public List<SpaceReadModel> getSpaces() {
        return spaces;
    }

    public UUID getId() {
        return id;
    }
}
