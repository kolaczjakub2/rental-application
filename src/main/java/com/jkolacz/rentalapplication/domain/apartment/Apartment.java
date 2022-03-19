package com.jkolacz.rentalapplication.domain.apartment;

import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "APARTMENT")
public class Apartment {
    @Id
    @GeneratedValue
    private UUID id;
    private String ownerId;

    @Embedded
    private Address address;
    private String description;
    @ElementCollection
    private List<Room> rooms;

    public Apartment() {
    }

    Apartment(String ownerId, Address address, String description, List<Room> rooms) {
        this.ownerId = ownerId;
        this.address = address;
        this.description = description;
        this.rooms = rooms;
    }

    public Booking book(String tenantId, Period period, EventChannel eventChannel) {
        ApartmentBooked apartmentBooked = ApartmentBooked.create(id(), ownerId, tenantId, period);
        eventChannel.publish(apartmentBooked);
        return Booking.apartment(id(), tenantId, period);
    }

    public String id() {
        return getId().toString();
    }

    public UUID getId() {
        return id;
    }
}
