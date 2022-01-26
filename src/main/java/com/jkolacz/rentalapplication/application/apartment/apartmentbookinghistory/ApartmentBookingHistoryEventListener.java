package com.jkolacz.rentalapplication.application.apartment.apartmentbookinghistory;

import com.jkolacz.rentalapplication.domain.apartment.apartmentbookinghistory.ApartmentBooking;
import com.jkolacz.rentalapplication.domain.apartment.apartmentbookinghistory.ApartmentBookingHistory;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentBooked;
import com.jkolacz.rentalapplication.domain.apartment.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import org.springframework.context.event.EventListener;

public class ApartmentBookingHistoryEventListener {
    private final ApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

    public ApartmentBookingHistoryEventListener(ApartmentBookingHistoryRepository apartmentBookingHistoryRepository) {
        this.apartmentBookingHistoryRepository = apartmentBookingHistoryRepository;
    }

    @EventListener
    public void consume(ApartmentBooked apartmentBooked) {
        ApartmentBookingHistory apartmentBookingHistory = getApartmentBookingHistoryFor(apartmentBooked.getApartmentId());

        apartmentBookingHistory.add(ApartmentBooking.start(apartmentBooked.getOwnerId(), apartmentBooked.getTenantId(),
                apartmentBooked.getPeriodStart(), apartmentBooked.getPeriodEnd()));

        apartmentBookingHistoryRepository.save(apartmentBookingHistory);
    }

    private ApartmentBookingHistory getApartmentBookingHistoryFor(String apartmentId) {
        if (apartmentBookingHistoryRepository.existFor(apartmentId)) {
            return apartmentBookingHistoryRepository.findFor(apartmentId);
        } else {
            return new ApartmentBookingHistory(apartmentId);
        }
    }
}
