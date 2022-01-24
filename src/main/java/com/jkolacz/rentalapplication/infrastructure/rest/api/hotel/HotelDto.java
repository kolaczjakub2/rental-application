package com.jkolacz.rentalapplication.infrastructure.rest.api.hotel;

public class HotelDto {
    private final String name;
    private final String street;
    private final String buildingNumber;
    private final String postalCode;
    private final String city;
    private final String country;

    public HotelDto(String name, String street, String buildingNumber, String postalCode, String city, String country) {
        this.name = name;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
