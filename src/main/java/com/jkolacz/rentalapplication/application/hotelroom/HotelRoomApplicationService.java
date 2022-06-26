package com.jkolacz.rentalapplication.application.hotelroom;

import com.jkolacz.rentalapplication.domain.booking.Booking;
import com.jkolacz.rentalapplication.domain.booking.BookingRepository;
import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoom;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoomEventsPublisher;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoomRepository;

public class HotelRoomApplicationService {
    private final HotelRepository hotelRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final BookingRepository bookingRepository;
    private final HotelRoomEventsPublisher hotelRoomEventsPublisher;

    HotelRoomApplicationService(
            HotelRepository hotelRepository, HotelRoomRepository hotelRoomRepository, BookingRepository bookingRepository, HotelRoomEventsPublisher hotelRoomEventsPublisher) {
        this.hotelRepository = hotelRepository;
        this.hotelRoomRepository = hotelRoomRepository;
        this.bookingRepository = bookingRepository;
        this.hotelRoomEventsPublisher = hotelRoomEventsPublisher;
    }

    public String add(HotelRoomDto hotelRoomDto) {
        Hotel hotel = hotelRepository.findById(hotelRoomDto.getHotelId());

        hotel.addRoom(hotelRoomDto.getNumber(), hotelRoomDto.getSpacesDefinition(), hotelRoomDto.getDescription());

        hotelRepository.save(hotel);

       return hotel.getIdOfRoom(hotelRoomDto.getNumber());
    }

    public String book(HotelRoomBookingDto hotelRoomBookingDto) {
        HotelRoom hotelRoom = hotelRoomRepository.findById(hotelRoomBookingDto.getHotelRoomId());

        Booking booking = hotelRoom.book(hotelRoomBookingDto.getTenantId(), hotelRoomBookingDto.getDays(), hotelRoomEventsPublisher);

        return bookingRepository.save(booking);
    }
}
