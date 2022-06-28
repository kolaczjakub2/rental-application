package com.jkolacz.rentalapplication.application.hotelroomoffer;

import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOfferDomainService;
import com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOfferRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HotelRoomOfferApplicationServiceFactory {
    @Bean
    HotelRoomOfferApplicationService hotelRoomOfferApplicationService(HotelRoomOfferRepository hotelRoomOfferRepository, HotelRepository hotelRepository) {
        return new HotelRoomOfferApplicationService(hotelRoomOfferRepository, hotelRepository, new HotelRoomOfferDomainService());
    }
}
