package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartmentoffer;

import com.jkolacz.rentalapplication.domain.apartmentoffer.ApartmentOffer;
import com.jkolacz.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaApartmentOfferRepository implements ApartmentOfferRepository {
    private final SpringJpaApartmentOfferRepository repository;

    JpaApartmentOfferRepository(SpringJpaApartmentOfferRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID save(ApartmentOffer apartmentOffer) {
        return repository.save(apartmentOffer).id();
    }

    @Override
    public boolean existByApartmentId(String apartmentId) {
        return repository.existsByApartmentId(apartmentId);
    }

    @Override
    public ApartmentOffer findByApartmentId(String apartmentId) {
        return repository.findByApartmentId(apartmentId);
    }
}
