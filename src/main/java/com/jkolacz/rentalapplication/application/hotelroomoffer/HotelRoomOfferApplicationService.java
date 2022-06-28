package com.jkolacz.rentalapplication.application.hotelroomoffer;

import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOfferDomainService;
import com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOfferRepository;

import java.util.UUID;

public class HotelRoomOfferApplicationService {
    private final HotelRoomOfferRepository hotelRoomOfferRepository;
    private final HotelRepository hotelRepository;
    private final HotelRoomOfferDomainService hotelRoomOfferDomainService;

    HotelRoomOfferApplicationService(
            HotelRoomOfferRepository hotelRoomOfferRepository, HotelRepository hotelRepository, HotelRoomOfferDomainService hotelRoomOfferDomainService) {
        this.hotelRoomOfferRepository = hotelRoomOfferRepository;
        this.hotelRepository = hotelRepository;
        this.hotelRoomOfferDomainService = hotelRoomOfferDomainService;
    }

    public UUID add(HotelRoomOfferDto dto) {
        Hotel hotel = hotelRepository.findById(dto.getHotelId());

        HotelRoomOffer hotelRoomOffer = hotelRoomOfferDomainService.createOfferForHotelRoom(hotel, dto.asDto());

        return hotelRoomOfferRepository.save(hotelRoomOffer);
    }
}
