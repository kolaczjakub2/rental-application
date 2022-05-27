package com.jkolacz.rentalapplication.domain.apartment;

import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

class ApartmentEventsPublisherTest {

    private final EventChannel eventChannel = Mockito.mock(EventChannel.class);
    private final ApartmentEventsPublisher publisher = new ApartmentEventsPublisher(new EventIdFactory(), eventChannel);



    @Test
    void shouldCreateApartmentBooked() {
        ArgumentCaptor<ApartmentBooked> captor = ArgumentCaptor.forClass(ApartmentBooked.class);
        String apartmentId = "1234";
        String ownerId = "5678";
        String tenantId = "789";
        LocalDate start = LocalDate.of(2020, 10, 11);
        LocalDate end = LocalDate.of(2020, 10, 18);
        Period period = new Period(start, end);
        publisher.publishApartmentBooked(apartmentId, ownerId, tenantId, period);
        LocalDateTime beforeNow = LocalDateTime.now().minusSeconds(1);
        then(eventChannel).should().publish(captor.capture());

        ApartmentBooked actual = captor.getValue();
        assertThat(actual.getEventId()).matches(Pattern.compile("[0-9a-z\\-]{36}"));
        assertThat(actual.getEventCreationDateTime())
                .isBefore(LocalDateTime.now().plusNanos(1))
                .isAfter(beforeNow);
        assertThat(actual.getApartmentId()).isEqualTo(apartmentId);
        assertThat(actual.getOwnerId()).isEqualTo(ownerId);
        assertThat(actual.getTenantId()).isEqualTo(tenantId);
        assertThat(actual.getPeriodStart()).isEqualTo(start);
        assertThat(actual.getPeriodEnd()).isEqualTo(end);
    }

}