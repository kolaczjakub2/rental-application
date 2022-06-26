package com.jkolacz.rentalapplication.application.hotelroomoffer;

import com.jkolacz.rentalapplication.domain.hotel.HotelRoomNotFoundException;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoomRepository;
import com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOfferRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOffer.Builder.hotelRoomOffer;

@Service
public class HotelRoomOfferApplicationService {
    private final HotelRoomOfferRepository hotelRoomOfferRepository;
    private final HotelRoomRepository hotelRoomRepository;

    HotelRoomOfferApplicationService(HotelRoomOfferRepository hotelRoomOfferRepository, HotelRoomRepository hotelRoomRepository) {
        this.hotelRoomOfferRepository = hotelRoomOfferRepository;
        this.hotelRoomRepository = hotelRoomRepository;
    }

    public UUID add(HotelRoomOfferDto dto) {
        if (hotelRoomRepository.existById(dto.getHotelRoomId())) {
            HotelRoomOffer hotelRoomOffer = hotelRoomOffer()
                    .withHotelRoomId(dto.getHotelRoomId())
                    .withPrice(dto.getPrice())
                    .withAvailability(dto.getStart(), dto.getEnd())
                    .build();

            return hotelRoomOfferRepository.save(hotelRoomOffer);
        } else {
            throw new HotelRoomNotFoundException(dto.getHotelRoomId());
        }
    }
}
