package com.jkolacz.rentalapplication.application.hotelRoom;

import com.jkolacz.rentalapplication.domain.apartment.Booking;
import com.jkolacz.rentalapplication.domain.apartment.BookingRepository;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoom;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomFactory;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class HotelRoomApplicationService {
    private final HotelRoomRepository hotelRoomRepository;
    private final BookingRepository bookingRepository;
    private final EventChannel eventChannel;


    public HotelRoomApplicationService(HotelRoomRepository hotelRoomRepository, BookingRepository bookingRepository, EventChannel eventChannel) {
        this.hotelRoomRepository = hotelRoomRepository;
        this.bookingRepository = bookingRepository;
        this.eventChannel = eventChannel;
    }

    public String add(String hotelId, Integer number, String description, Map<String, Double> roomsDefinition) {
        HotelRoom hotelRoom = new HotelRoomFactory().create(hotelId, number, description, roomsDefinition);
        return hotelRoomRepository.save(hotelRoom);
    }

    public String book(String id, String tenantId, List<LocalDate> days) {
        HotelRoom hotelRoom = hotelRoomRepository.findById(id);
        Booking booking = hotelRoom.book(tenantId, days, eventChannel);

        return bookingRepository.save(booking);
    }

}
