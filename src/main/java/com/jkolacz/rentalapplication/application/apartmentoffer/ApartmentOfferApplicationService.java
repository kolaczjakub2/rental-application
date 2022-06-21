package com.jkolacz.rentalapplication.application.apartmentoffer;

import com.jkolacz.rentalapplication.domain.apartment.ApartmentNotFoundException;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;
import com.jkolacz.rentalapplication.domain.apartmentoffer.ApartmentOffer;
import com.jkolacz.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.jkolacz.rentalapplication.domain.apartmentoffer.ApartmentOffer.Builder.apartmentOffer;

@Service
public class ApartmentOfferApplicationService {
    private final ApartmentOfferRepository apartmentOfferRepository;
    private final ApartmentRepository apartmentRepository;

    ApartmentOfferApplicationService(ApartmentOfferRepository apartmentOfferRepository, ApartmentRepository apartmentRepository) {
        this.apartmentOfferRepository = apartmentOfferRepository;
        this.apartmentRepository = apartmentRepository;
    }

    public UUID add(ApartmentOfferDto dto) {
        if (apartmentRepository.existById(dto.getApartmentId())) {
            ApartmentOffer apartmentOffer = apartmentOffer()
                    .withApartmentId(dto.getApartmentId())
                    .withPrice(dto.getPrice())
                    .withAvailability(dto.getStart(), dto.getEnd())
                    .build();

            return apartmentOfferRepository.save(apartmentOffer);
        } else {
            throw new ApartmentNotFoundException(dto.getApartmentId());
        }
    }
}
