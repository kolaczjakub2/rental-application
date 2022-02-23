package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartment;

import com.jkolacz.rentalapplication.domain.apartment.Apartment;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaApartmentRepository implements ApartmentRepository {

    private final SpringApartmentJpaRepository springApartmentJpaRepository;

    public JpaApartmentRepository(SpringApartmentJpaRepository springApartmentJpaRepository) {
        this.springApartmentJpaRepository = springApartmentJpaRepository;
    }

    @Override
    public String save(Apartment apartment) {
        return springApartmentJpaRepository.save(apartment).id();
    }

    @Override
    public Apartment findById(String id) {
        return springApartmentJpaRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ApartmentNotFoundException(id));
    }
}
