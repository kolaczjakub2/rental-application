package com.jkolacz.rentalapplication.application.apartment;

import com.jkolacz.rentalapplication.domain.apartment.Apartment;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;
import com.jkolacz.rentalapplication.domain.apartment.Booking;
import com.jkolacz.rentalapplication.domain.apartment.BookingRepository;
import com.jkolacz.rentalapplication.domain.apartment.Period;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.jkolacz.rentalapplication.domain.apartment.Apartment.Builder.apartment;

@Service
public class ApartmentApplicationService {

    private final ApartmentRepository apartmentRepository;
    private final EventChannel eventChannel;
    private final BookingRepository bookingRepository;

    public ApartmentApplicationService(ApartmentRepository apartmentRepository, EventChannel eventChannel, BookingRepository bookingRepository) {
        this.apartmentRepository = apartmentRepository;
        this.eventChannel = eventChannel;
        this.bookingRepository = bookingRepository;
    }

    public String add(ApartmentDto apartmentDto) {
        Apartment apartment = apartment()
                .withOwnerId(apartmentDto.getOwnerId())
                .withStreet(apartmentDto.getStreet())
                .withPostalCode(apartmentDto.getPostalCode())
                .withHouseNumber(apartmentDto.getHouseNumber())
                .withApartmentNumber(apartmentDto.getApartmentNumber())
                .withCity(apartmentDto.getCity())
                .withCountry(apartmentDto.getCountry())
                .withDescription(apartmentDto.getDescription())
                .withRoomsDefinition(apartmentDto.getRoomsDefinition())
                .build();

        return apartmentRepository.save(apartment);
    }

    @SuppressWarnings("checkstyle:ParameterNumber")
    public String book(String id, String tenantId, LocalDate start, LocalDate end) {
        Apartment apartment = apartmentRepository.findById(id);
        Period period = new Period(start, end);
        Booking booking = apartment.book(tenantId, period, eventChannel);

        return bookingRepository.save(booking);
    }
}
