package com.jkolacz.rentalapplication.application.booking;

import com.jkolacz.rentalapplication.domain.agreement.Agreement;
import com.jkolacz.rentalapplication.domain.agreement.AgreementRepository;
import com.jkolacz.rentalapplication.domain.booking.Booking;
import com.jkolacz.rentalapplication.domain.booking.BookingDomainService;
import com.jkolacz.rentalapplication.domain.booking.BookingEventsPublisher;
import com.jkolacz.rentalapplication.domain.booking.BookingRepository;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.Optional;

public class BookingCommandHandler {
    private final BookingRepository bookingRepository;
    private final AgreementRepository agreementRepository;
    private final BookingDomainService bookingDomainService;
    private final BookingEventsPublisher bookingEventsPublisher;

    BookingCommandHandler(
            BookingRepository bookingRepository, AgreementRepository agreementRepository, BookingDomainService bookingDomainService,
            BookingEventsPublisher bookingEventsPublisher) {
        this.bookingRepository = bookingRepository;
        this.agreementRepository = agreementRepository;
        this.bookingDomainService = bookingDomainService;
        this.bookingEventsPublisher = bookingEventsPublisher;
    }

    @EventListener
    public void reject(BookingReject bookingReject) {
        Booking booking = bookingRepository.findById(bookingReject.getBookingId());

        booking.reject(bookingEventsPublisher);

        bookingRepository.save(booking);
    }

    @EventListener
    public void accept(BookingAccept bookingAccept) {
        Booking booking = bookingRepository.findById(bookingAccept.getId());
        List<Booking> bookings = bookingRepository.findAllBy(booking.rentalPlaceIdentifier());

        Optional<Agreement> agreement = bookingDomainService.accept(booking, bookings);

        bookingRepository.save(booking);
        agreement.ifPresent(agreementRepository::save);
    }
}
