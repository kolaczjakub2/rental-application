package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelroom;

import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelroom.SpringJpaHotelRoomRepository;
import com.jkolacz.rentalapplication.rentalapplication.infrastructure.persistence.jpa.hotelroom.SpringJpaHotelRoomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SpringJpaHotelRoomTestRepository {
    private final SpringJpaHotelRoomRepository repository;

    SpringJpaHotelRoomTestRepository(SpringJpaHotelRoomRepository repository) {
        this.repository = repository;
    }

    public void deleteById(String hotelRoomId) {
        repository.deleteById(UUID.fromString(hotelRoomId));
    }

    public void deleteAll(List<String> ids) {
        ids.forEach(this::deleteById);
    }
}