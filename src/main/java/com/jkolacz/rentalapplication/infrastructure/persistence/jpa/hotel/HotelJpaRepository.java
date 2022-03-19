package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HotelJpaRepository implements HotelRepository {

    private final SpringHotelJpaRepository springHotelJpaRepository;

    public HotelJpaRepository(SpringHotelJpaRepository springHotelJpaRepository) {
        this.springHotelJpaRepository = springHotelJpaRepository;
    }

    @Override
    public void save(Hotel hotel) {
        springHotelJpaRepository.save(hotel);
    }
}
