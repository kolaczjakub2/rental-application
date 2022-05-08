package com.jkolacz.rentalapplication.query.apartment;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "APARTMENT")
public class ApartmentReadModel {

    @Id
    @GeneratedValue
    private UUID id;
    private String ownerId;

    private String street;
    private String postalCode;
    private String houseNumber;
    private String apartmentNumber;
    private String city;
    private String country;
    private String description;
    @ElementCollection
    private List<RoomReadModel> rooms;

    public UUID getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public List<RoomReadModel> getRooms() {
        return rooms;
    }
}
