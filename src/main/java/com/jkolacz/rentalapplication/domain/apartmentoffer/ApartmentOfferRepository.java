package com.jkolacz.rentalapplication.domain.apartmentoffer;

import java.util.UUID;

public interface ApartmentOfferRepository {
    UUID save(ApartmentOffer apartmentOffer);
}
