package com.jkolacz.rentalapplication.domain.hotel;


import javax.persistence.Embeddable;

@Embeddable
class Address {
    private  String street;
    private  String buildingNumber;
    private  String postalCode;
    private  String city;
    private  String country;

    private Address(){
    }

    Address(String street, String buildingNumber, String postalCode, String city, String country) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
