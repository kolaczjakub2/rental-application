package com.jkolacz.rentalapplication.application.booking;

import com.jkolacz.rentalapplication.domain.apartment.Booking;
import com.jkolacz.rentalapplication.domain.apartment.BookingRepository;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import org.springframework.context.event.EventListener;

public class BookingCommandHandler {

    private final BookingRepository bookingRepository;
    private final EventChannel eventChannel;

    public BookingCommandHandler(BookingRepository bookingRepository, EventChannel eventChannel) {
        this.bookingRepository = bookingRepository;
        this.eventChannel = eventChannel;
    }

    @EventListener
    public void reject(BookingReject bookingReject) {
        Booking booking = bookingRepository.findById(bookingReject.getBookingId());
        booking.reject();

        bookingRepository.save(booking);
    }


    @EventListener
    public void accept(BookingAccept bookingAccept){
        Booking booking = bookingRepository.findById(bookingAccept.getBookingId());
        booking.accept(eventChannel);

        bookingRepository.save(booking);

    }
}
