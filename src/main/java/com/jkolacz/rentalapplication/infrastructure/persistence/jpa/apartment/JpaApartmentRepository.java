package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartment;

import com.jkolacz.rentalapplication.domain.apartment.Apartment;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaApartmentRepository implements ApartmentRepository {

    private final SpringApartmentJpaRepository springApartmentJpaRepository;

    public JpaApartmentRepository(SpringApartmentJpaRepository springApartmentJpaRepository) {
        this.springApartmentJpaRepository = springApartmentJpaRepository;
    }

    @Override
    public void save(Apartment apartment) {

    }

    @Override
    public Apartment findById(String id) {
        throw new ApartmentNotFoundException(id);
    }
}
