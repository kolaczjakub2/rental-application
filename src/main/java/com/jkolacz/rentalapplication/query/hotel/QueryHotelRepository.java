package com.jkolacz.rentalapplication.query.hotel;

import org.springframework.stereotype.Repository;

@Repository
public class QueryHotelRepository {
    private final SpringQueryHotelRepository springQueryHotelRepository;

    public QueryHotelRepository(SpringQueryHotelRepository springQueryHotelRepository) {
        this.springQueryHotelRepository = springQueryHotelRepository;
    }

    public Iterable<HotelReadModel> findAll() {
        return springQueryHotelRepository.findAll();
    }
}
