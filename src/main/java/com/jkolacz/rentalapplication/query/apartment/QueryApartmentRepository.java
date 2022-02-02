package com.jkolacz.rentalapplication.query.apartment;

public class QueryApartmentRepository {
    private final SpringQueryApartmentRepository springQueryApartmentRepository;
    private final SpringQueryApartmentBookingHistoryRepository springQueryApartmentBookingHistoryRepository;

    public QueryApartmentRepository(SpringQueryApartmentRepository springQueryApartmentRepository, SpringQueryApartmentBookingHistoryRepository springQueryApartmentBookingHistoryRepository) {
        this.springQueryApartmentRepository = springQueryApartmentRepository;
        this.springQueryApartmentBookingHistoryRepository = springQueryApartmentBookingHistoryRepository;
    }

    public Iterable<ApartmentReadModel> findAll() {
        return null;
    }

    public ApartmentDetails findById(String id) {
        ApartmentReadModel apartmentReadModel = null;
        ApartmentBookingHistoryReadModel apartmentBookingHistoryReadModel = null;
        return new ApartmentDetails(apartmentReadModel, apartmentBookingHistoryReadModel);
    }
}
