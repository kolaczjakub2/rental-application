package com.jkolacz.rentalapplication.application.hotelroomoffer;

import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoomNotFoundException;
import com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOfferRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOffer.Builder.hotelRoomOffer;

@Service
public class HotelRoomOfferApplicationService {
    private final HotelRoomOfferRepository hotelRoomOfferRepository;
    private final HotelRepository hotelRepository;

    HotelRoomOfferApplicationService(HotelRoomOfferRepository hotelRoomOfferRepository, HotelRepository hotelRepository) {
        this.hotelRoomOfferRepository = hotelRoomOfferRepository;
        this.hotelRepository = hotelRepository;
    }

    public UUID add(HotelRoomOfferDto dto) {
        Hotel hotel = hotelRepository.findById(dto.getHotelId());

        if (hotel.hasRoomWithNumber(dto.getNumber())) {
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
