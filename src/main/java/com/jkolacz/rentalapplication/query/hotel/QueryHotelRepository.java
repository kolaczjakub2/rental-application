package com.jkolacz.rentalapplication.query.hotel;

public class QueryHotelRepository {
    private final SpringQueryHotelRepository springQueryHotelRepository;

    public QueryHotelRepository(SpringQueryHotelRepository springQueryHotelRepository) {
        this.springQueryHotelRepository = springQueryHotelRepository;
    }


    public HotelReadModel findById(String id) {
        return springQueryHotelRepository.findById(id).get();
    }
}
