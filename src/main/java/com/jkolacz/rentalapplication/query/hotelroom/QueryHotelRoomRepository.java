package com.jkolacz.rentalapplication.query.hotelroom;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class QueryHotelRoomRepository {
    private final SpringJpaQueryHotelRoomRepository repository;

    public QueryHotelRoomRepository(SpringJpaQueryHotelRoomRepository repository) {
        this.repository = repository;
    }

    public Iterable<HotelRoomReadModel> findAll(UUID hotelId) {
        return repository.findAllByHotelId(hotelId);
    }
}
