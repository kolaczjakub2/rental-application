package com.jkolacz.rentalapplication.domain.hotelRoom;

import com.google.common.collect.ImmutableMap;
import com.jkolacz.rentalapplication.domain.apartment.Booking;
import com.jkolacz.rentalapplication.domain.apartment.BookingAssertion;
import com.jkolacz.rentalapplication.domain.eventchannel.ApartmentEventsPublisher;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class HotelRoomTest {
    private static final String HOTEL_ID = UUID.randomUUID().toString();
    private static final int ROOM_NUMBER = 13;
    private static final Map<String, Double> SPACES_DEFINITION = ImmutableMap.of("RoomOne", 20.0, "RoomTwo", 20.0);
    private static final String DESCRIPTION = "What a lovely place";
    private static final String TENANT_ID = "325426";
    private static final List<LocalDate> DAYS = asList(LocalDate.now(), LocalDate.now().plusDays(1));
    private final HotelRoomFactory factory = new HotelRoomFactory();
    private final ApartmentEventsPublisher eventChannel = mock(ApartmentEventsPublisher.class);

    @Test
    void shouldCreateHotelRoomWithAllRequiredInformation() {
        HotelRoom actual = createHotelRoom();

        HotelRoomAssertion.assertThat(actual)
                .hasHotelIdEqualsTo(HOTEL_ID)
                .hasNumberEqualsTo(ROOM_NUMBER)
                .hasSpacesDefinitionEqualTo(SPACES_DEFINITION)
                .hasDescriptionEqualsTo(DESCRIPTION);
    }

    @Test
    void shouldCreateBookingOnceBooked() {
        HotelRoom hotelRoom = createHotelRoom();
        Booking actual = hotelRoom.book(TENANT_ID, DAYS, eventChannel);

        BookingAssertion.assertThat(actual)
                .isHotelRoom()
                .hasTenantIdEqualTo(TENANT_ID)
                .containsAllDays(DAYS);
    }

    @Test
    void shouldPublishHotelRoomBooked() {
        ArgumentCaptor<HotelRoomBooked> captor= ArgumentCaptor.forClass(HotelRoomBooked.class);
        HotelRoom hotelRoom = createHotelRoom();
        hotelRoom.book(TENANT_ID, DAYS, eventChannel);

        then(eventChannel).should().publish(captor.capture());
        HotelRoomBooked actual = captor.getValue();

        HotelRoomBookedAssertion.assertThat(actual)
                .hasTenantIdEqualsTo(TENANT_ID)
                .hasHotelIdEqualsTo(HOTEL_ID)
                .containsExactlyElementsOf(DAYS);

    }

    private HotelRoom createHotelRoom() {
        return factory.create(HOTEL_ID, ROOM_NUMBER, DESCRIPTION,SPACES_DEFINITION);
    }
}