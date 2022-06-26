package com.jkolacz.rentalapplication.application.apartmentbookinghistory;

import com.jkolacz.rentalapplication.domain.apartment.ApartmentBooked;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.BookingPeriod;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApartmentBookingHistoryEventListener {
    private final ApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

    public ApartmentBookingHistoryEventListener(ApartmentBookingHistoryRepository apartmentBookingHistoryRepository) {
        this.apartmentBookingHistoryRepository = apartmentBookingHistoryRepository;
    }

    @EventListener
    public void consume(ApartmentBooked apartmentBooked) {
        ApartmentBookingHistory apartmentBookingHistory = getApartmentBookingHistoryFor(apartmentBooked.getApartmentId());
        BookingPeriod bookingPeriod = new BookingPeriod(apartmentBooked.getPeriodStart(), apartmentBooked.getPeriodEnd());

        apartmentBookingHistory.addBookingStart(apartmentBooked.getEventCreationDateTime(), apartmentBooked.getOwnerId(), apartmentBooked.getTenantId(), bookingPeriod);

        apartmentBookingHistoryRepository.save(apartmentBookingHistory);
    }

    private ApartmentBookingHistory getApartmentBookingHistoryFor(String apartmentId) {
        if (apartmentBookingHistoryRepository.existsFor(apartmentId)) {
            return apartmentBookingHistoryRepository.findFor(apartmentId);
        } else {
            return new ApartmentBookingHistory(apartmentId);
        }
    }
}
