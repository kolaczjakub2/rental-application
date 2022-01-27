package com.jkolacz.rentalapplication.application.hotelBookingHistory;

import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomBooked;
import com.jkolacz.rentalapplication.domain.hotelRoomBookingHistory.HotelBookingHistory;
import com.jkolacz.rentalapplication.domain.hotelRoomBookingHistory.HotelBookingHistoryRepository;
import org.springframework.context.event.EventListener;

public class HotelBookingHistoryEventListener {
    private final HotelBookingHistoryRepository hotelBookingHistoryRepository;

    public HotelBookingHistoryEventListener(HotelBookingHistoryRepository hotelBookingHistoryRepository) {
        this.hotelBookingHistoryRepository = hotelBookingHistoryRepository;
    }


    @EventListener
    public void consume(HotelRoomBooked hotelRoomBooked) {
        HotelBookingHistory hotelBookingHistory = getHotelRoomBookingHistoryFor(hotelRoomBooked.getHotelId());

        hotelBookingHistory.add(hotelRoomBooked.getHotelRoomId(),hotelRoomBooked.getEventCreationDateTime(),
                hotelRoomBooked.getTenantId(), hotelRoomBooked.getDays());

        hotelBookingHistoryRepository.save(hotelBookingHistory);
    }

    private HotelBookingHistory getHotelRoomBookingHistoryFor(String hotelId) {
        if (hotelBookingHistoryRepository.existsFor(hotelId)) {
            return hotelBookingHistoryRepository.findFor(hotelId);
        } else {
            return new HotelBookingHistory(hotelId);
        }
    }
}
