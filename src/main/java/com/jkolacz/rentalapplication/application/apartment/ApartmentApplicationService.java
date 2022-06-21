package com.jkolacz.rentalapplication.application.apartment;

import com.jkolacz.rentalapplication.domain.apartment.Apartment;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentEventsPublisher;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;
import com.jkolacz.rentalapplication.domain.apartment.Period;
import com.jkolacz.rentalapplication.domain.booking.Booking;
import com.jkolacz.rentalapplication.domain.booking.BookingRepository;

import static com.jkolacz.rentalapplication.domain.apartment.Apartment.Builder.apartment;

public class ApartmentApplicationService {
    private final ApartmentRepository apartmentRepository;
    private final BookingRepository bookingRepository;
    private final ApartmentEventsPublisher apartmentEventsPublisher;

    ApartmentApplicationService(ApartmentRepository apartmentRepository, BookingRepository bookingRepository, ApartmentEventsPublisher apartmentEventsPublisher) {
        this.apartmentRepository = apartmentRepository;
        this.bookingRepository = bookingRepository;
        this.apartmentEventsPublisher = apartmentEventsPublisher;
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
                .withSpacesDefinition(apartmentDto.getSpacesDefinition())
                .build();

        return apartmentRepository.save(apartment);
    }

    public String book(ApartmentBookingDto apartmentBookingDto) {
        Apartment apartment = apartmentRepository.findById(apartmentBookingDto.getApartmentId());
        Period period = new Period(apartmentBookingDto.getStart(), apartmentBookingDto.getEnd());

        Booking booking = apartment.book(apartmentBookingDto.getTenantId(), period, apartmentEventsPublisher);

        return bookingRepository.save(booking);
    }
}
