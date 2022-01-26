package com.jkolacz.rentalapplication.domain.apartment.apartmentbookinghistory;

import java.time.LocalDate;

public class ApartmentBooking {
    private static String ownerId;
    private static String tenantId;
    private static LocalDate periodStart;
    private static LocalDate periodEnd;

    public static ApartmentBooking start(String ownerId, String tenantId, LocalDate periodStart, LocalDate periodEnd) {
        ApartmentBooking.ownerId = ownerId;
        ApartmentBooking.tenantId = tenantId;
        ApartmentBooking.periodStart = periodStart;
        ApartmentBooking.periodEnd = periodEnd;
    }
}
