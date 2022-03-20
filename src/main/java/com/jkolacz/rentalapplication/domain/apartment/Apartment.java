package com.jkolacz.rentalapplication.domain.apartment;

import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @ElementCollection
    private List<Room> rooms;

    private String description;

    private Apartment() {}

    Apartment(String ownerId, Address address, List<Room> rooms, String description) {
        this.ownerId = ownerId;
        this.address = address;
        this.rooms = rooms;
        this.description = description;
    }

    public Booking book(String tenantId, Period period, EventChannel eventChannel) {
        ApartmentBooked apartmentBooked = ApartmentBooked.create(id(), ownerId, tenantId, period);
        eventChannel.publish(apartmentBooked);

        return Booking.apartment(id(), tenantId, period);
    }

    public UUID getId() {
        return id;
    }

    public String id() {
        return getId().toString();
    }
}