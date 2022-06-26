package com.jkolacz.rentalapplication.application.hotelroom;

import com.jkolacz.rentalapplication.domain.booking.Booking;
import com.jkolacz.rentalapplication.domain.booking.BookingRepository;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoom;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoomEventsPublisher;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoomRepository;

import static com.jkolacz.rentalapplication.domain.hotel.HotelRoom.Builder.hotelRoom;

public class HotelRoomApplicationService {
    private final HotelRoomRepository hotelRoomRepository;
    private final BookingRepository bookingRepository;
    private final HotelRoomEventsPublisher hotelRoomEventsPublisher;

    HotelRoomApplicationService(
            HotelRoomRepository hotelRoomRepository, BookingRepository bookingRepository, HotelRoomEventsPublisher hotelRoomEventsPublisher) {
        this.hotelRoomRepository = hotelRoomRepository;
        this.bookingRepository = bookingRepository;
        this.hotelRoomEventsPublisher = hotelRoomEventsPublisher;
    }

    public String add(HotelRoomDto hotelRoomDto) {
        HotelRoom hotelRoom = hotelRoom()
                .withHotelId(hotelRoomDto.getHotelId())
                .withNumber(hotelRoomDto.getNumber())
                .withSpacesDefinition(hotelRoomDto.getSpacesDefinition())
                .withDescription(hotelRoomDto.getDescription())
                .build();

        return hotelRoomRepository.save(hotelRoom);
    }

    public String book(HotelRoomBookingDto hotelRoomBookingDto) {
        HotelRoom hotelRoom = hotelRoomRepository.findById(hotelRoomBookingDto.getHotelRoomId());

        Booking booking = hotelRoom.book(hotelRoomBookingDto.getTenantId(), hotelRoomBookingDto.getDays(), hotelRoomEventsPublisher);

        return bookingRepository.save(booking);
    }
}
