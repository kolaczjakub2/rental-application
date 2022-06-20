package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotel.SpringJpaHotelRepository;
import com.jkolacz.rentalapplication.rentalapplication.infrastructure.persistence.jpa.hotel.SpringJpaHotelRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SpringJpaHotelTestRepository {
    private final SpringJpaHotelRepository repository;

    SpringJpaHotelTestRepository(SpringJpaHotelRepository repository) {
        this.repository = repository;
    }

    public void deleteAll(List<String> ids) {
        ids.forEach(id -> repository.deleteById(UUID.fromString(id)));
    }
}