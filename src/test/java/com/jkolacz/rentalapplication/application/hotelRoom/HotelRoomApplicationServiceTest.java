package com.jkolacz.rentalapplication.application.hotelRoom;

import com.google.common.collect.ImmutableMap;
import com.jkolacz.rentalapplication.domain.apartment.Booking;
import com.jkolacz.rentalapplication.domain.apartment.BookingAssertion;
import com.jkolacz.rentalapplication.domain.apartment.BookingRepository;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoom;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomAssertion;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomFactory;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class HotelRoomApplicationServiceTest {

    private static final String HOTEL_ID = UUID.randomUUID().toString();
    private static final int ROOM_NUMBER = 13;
    private static final Map<String, Double> SPACES_DEFINITION = ImmutableMap.of("RoomOne", 20.0, "RoomTwo", 20.0);
    private static final String DESCRIPTION = "What a lovely place";
    private static final String TENANT_ID = "4321";
    private static final List<LocalDate> DAYS = asList(LocalDate.now(), LocalDate.now().plusDays(1));
    private static final String HOTEL_ROOM_ID = "7821321";

    private final HotelRoomRepository hotelRoomRepository = Mockito.mock(HotelRoomRepository.class);
    private final BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);
    private final EventChannel eventChannel = Mockito.mock(EventChannel.class);
    private final HotelRoomApplicationService service = new HotelRoomApplicationService(hotelRoomRepository, bookingRepository, eventChannel);

    @Test
    void shouldCreateBookingWhenHotelRoomBooked() {
        String hotelRoomId = "1234";
        givenHotelRoom(hotelRoomId);
        service.book(hotelRoomId, TENANT_ID, DAYS);

        thenBookingShouldBeCreated();
    }

    @Test
    void shouldCreateHotelRoom() {
        ArgumentCaptor<HotelRoom> captor = ArgumentCaptor.forClass(HotelRoom.class);

        service.add(HOTEL_ID, ROOM_NUMBER, DESCRIPTION,SPACES_DEFINITION);

        then(hotelRoomRepository).should().save(captor.capture());
        HotelRoomAssertion.assertThat(captor.getValue())
                .hasHotelIdEqualsTo(HOTEL_ID)
                .hasNumberEqualsTo(ROOM_NUMBER)
                .hasSpacesDefinitionEqualTo(SPACES_DEFINITION)
                .hasDescriptionEqualsTo(DESCRIPTION);
    }

    @Test
    void shouldReturnIdOfNewHotelRoom() {
        given(hotelRoomRepository.save(any())).willReturn(HOTEL_ROOM_ID);

        String actual = service.add(HOTEL_ID, ROOM_NUMBER, DESCRIPTION,SPACES_DEFINITION);

        Assertions.assertThat(actual).isEqualTo(HOTEL_ROOM_ID);
    }

    private void thenBookingShouldBeCreated() {
        ArgumentCaptor<Booking> captor = ArgumentCaptor.forClass(Booking.class);
        then(bookingRepository).should().save(captor.capture());

        BookingAssertion.assertThat(captor.getValue())
                .isHotelRoom()
                .hasTenantIdEqualTo(TENANT_ID)
                .containsAllDays(DAYS);
    }

    private void givenHotelRoom(String hotelRoomId) {
        HotelRoom hotelRoom = createHotelRoom();
        given(hotelRoomRepository.findById(hotelRoomId)).willReturn(hotelRoom);
    }

    private HotelRoom createHotelRoom() {
        return new HotelRoomFactory().create("5678", 42, "This is very nice place", ImmutableMap.of("Room1", 30.0));
    }

}