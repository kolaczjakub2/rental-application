package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartment;

import com.jkolacz.rentalapplication.domain.apartment.Apartment;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;

public class ApartmentJpaRepository implements ApartmentRepository {

    private final SpringApartmentJpaRepository springApartmentJpaRepository;

    public ApartmentJpaRepository(SpringApartmentJpaRepository springApartmentJpaRepository) {
        this.springApartmentJpaRepository = springApartmentJpaRepository;
    }

    @Override
    public void save(Apartment apartment) {

    }

    @Override
    public Apartment findById(String id) {
        return null;
    }
}
