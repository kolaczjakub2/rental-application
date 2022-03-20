package com.jkolacz.rentalapplication.query.apartment;

public class ApartmentDetails {

    private static final ApartmentReadModel NO_APARTMENT = null;
    private static final ApartmentBookingHistoryReadModel NO_HISTORY = null;

    private final ApartmentReadModel apartment;
    private final ApartmentBookingHistoryReadModel bookingHistory;

    private ApartmentDetails(ApartmentReadModel apartment, ApartmentBookingHistoryReadModel bookingHistory) {
        this.apartment = apartment;
        this.bookingHistory = bookingHistory;
    }

    public static ApartmentDetails notExisting() {
        return new ApartmentDetails(NO_APARTMENT, NO_HISTORY);
    }

    public static ApartmentDetails withHistory(ApartmentReadModel apartmentReadModel, ApartmentBookingHistoryReadModel apartmentBookingHistoryReadModel) {
        return new ApartmentDetails(apartmentReadModel,apartmentBookingHistoryReadModel);
    }

    public ApartmentReadModel getApartment() {
        return apartment;
    }

    public ApartmentBookingHistoryReadModel getBookingHistory() {
        return bookingHistory;
    }
}
