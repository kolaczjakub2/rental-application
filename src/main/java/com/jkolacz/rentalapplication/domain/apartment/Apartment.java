package com.jkolacz.rentalapplication.domain.apartment;

import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
@Entity
@Table(name = "APARTMENT")
@SuppressWarnings("PMD.UnusedPrivateField")
public class Apartment {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String ownerId;

    @Embedded
    private Address address;

    @ElementCollection
    private List<Room> rooms;

    private String description;

    private Apartment() {
    }

    private Apartment(String ownerId, Address address, List<Room> rooms, String description) {
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
        if (id == null) {
            return null;
        }

        return id.toString();
    }

    public static class Builder {
        private String ownerId;
        private String street;
        private String postalCode;
        private String houseNumber;
        private String apartmentNumber;
        private String city;
        private String country;
        private String description;
        private Map<String, Double> roomsDefinition;

        private Builder() {}

        public static Builder apartment() {
            return new Builder();
        }

        public Builder withOwnerId(String ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder withHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder withApartmentNumber(String apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withRoomsDefinition(Map<String, Double> roomsDefinition) {
            this.roomsDefinition = roomsDefinition;
            return this;
        }

        public Apartment build() {
            return new Apartment(ownerId, address(), rooms(), description);
        }

        private Address address() {
            return new Address(street, postalCode, houseNumber, apartmentNumber, city, country);
        }

        private List<Room> rooms() {
            List<Room> rooms = new ArrayList<>();
            roomsDefinition.forEach((name, size) -> {
                SquareMeter squareMeter = new SquareMeter(size);
                rooms.add(new Room(name, squareMeter));
            });

            return rooms;
        }
    }
}
