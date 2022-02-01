package com.jkolacz.rentalapplication.query.hotelRoom;

public class QueryHotelRoomRepository {
    private final SpringQueryHotelRoomRepository springQueryHotelRoomRepository;

    public QueryHotelRoomRepository(SpringQueryHotelRoomRepository springQueryHotelRoomRepository) {
        this.springQueryHotelRoomRepository = springQueryHotelRoomRepository;
    }

    public Iterable<HotelRoomReadModel> findAll(String id) {
        return springQueryHotelRoomRepository.findAllByHotelId(id);
    }
}
