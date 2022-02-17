package com.jkolacz.rentalapplication.domain.apartment;

public class ApartmentBookedTestFactory {

    public static ApartmentBooked create(String apartmentId, String ownerId, String tenantId, Period period) {
        return ApartmentBooked.create(apartmentId,ownerId,tenantId,period);
    }

}