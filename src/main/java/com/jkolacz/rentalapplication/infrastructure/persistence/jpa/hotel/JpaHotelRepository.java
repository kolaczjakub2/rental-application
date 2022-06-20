package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import org.springframework.stereotype.Repository;

@Repository
class JpaHotelRepository implements HotelRepository {
    private final SpringJpaHotelRepository hotelRepository;

    JpaHotelRepository(SpringJpaHotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public String save(Hotel hotel) {
        return hotelRepository.save(hotel).id();
    }
}
