package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaHotelRepository implements HotelRepository {

    private final SpringHotelJpaRepository springHotelJpaRepository;

    public JpaHotelRepository(SpringHotelJpaRepository springHotelJpaRepository) {
        this.springHotelJpaRepository = springHotelJpaRepository;
    }

    @Override
    public String save(Hotel hotel) {
        return springHotelJpaRepository.save(hotel).getId().toString();
    }
}
