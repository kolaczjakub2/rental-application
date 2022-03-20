package com.jkolacz.rentalapplication.query.apartment;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
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
        Optional<ApartmentReadModel> found = springQueryApartmentRepository.findById(UUID.fromString(id));

        if (found.isPresent()) {

            ApartmentBookingHistoryReadModel apartmentBookingHistoryReadModel = springQueryApartmentBookingHistoryRepository.findById(id).get();
            return ApartmentDetails.withHistory(found.get(), apartmentBookingHistoryReadModel);
        } else {
            return ApartmentDetails.notExisting();
        }
    }
}
