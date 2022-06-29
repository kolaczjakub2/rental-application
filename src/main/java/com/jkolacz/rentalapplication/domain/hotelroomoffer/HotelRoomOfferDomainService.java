package com.jkolacz.rentalapplication.domain.hotelroomoffer;

import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoomNotFoundException;

import static com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOffer.Builder.hotelRoomOffer;

public class HotelRoomOfferDomainService {
    public HotelRoomOffer createOfferForHotelRoom(Hotel hotel, CreateHotelRoomOffer createHotelRoomOffer) {
        if (hotel.hasRoomWithNumber(createHotelRoomOffer.getNumber())) {
            return hotelRoomOffer()
                    .withHotelId(createHotelRoomOffer.getHotelId())
                    .withHotelRoomNumber(createHotelRoomOffer.getNumber())
                    .withPrice(createHotelRoomOffer.getPrice())
                    .withAvailability(createHotelRoomOffer.getStart(), createHotelRoomOffer.getEnd())
                    .build();
        } else {
            throw new HotelRoomNotFoundException(createHotelRoomOffer.getHotelId(), createHotelRoomOffer.getNumber());
        }
    }
}
