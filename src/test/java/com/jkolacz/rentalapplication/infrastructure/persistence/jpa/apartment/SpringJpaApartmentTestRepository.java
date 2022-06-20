package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartment;

import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartment.SpringJpaApartmentRepository;
import com.jkolacz.rentalapplication.rentalapplication.infrastructure.persistence.jpa.apartment.SpringJpaApartmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SpringJpaApartmentTestRepository {
    private final SpringJpaApartmentRepository repository;

    SpringJpaApartmentTestRepository(SpringJpaApartmentRepository repository) {
        this.repository = repository;
    }

    public void deleteById(String apartmentId) {
        repository.deleteById(UUID.fromString(apartmentId));
    }

    public void deleteAll(List<String> apartmentIds) {
        apartmentIds.forEach(this::deleteById);
    }
}