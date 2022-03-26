package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JpaApartmentBookingHistoryRepositoryIntegrationTest {
    @Autowired
    private ApartmentBookingHistoryRepository repository;
    @Autowired
    private SpringJpaApartmentBookingHistoryRepository jpaRepository;

    private String apartmentId;


    @AfterEach
    void deleteApartmentBookingHistory(){
        if(apartmentId!=null){
            jpaRepository.deleteById(apartmentId);
        }
    }

    @Test
    void shouldRecognizeApartmentBookingHistoryDoesNotExist(){
        String id = randomId();

        assertThat(repository.existFor(id)).isFalse();
    }

    @Test
    void shouldRecognizeApartmentBookingHistoryExist(){
        String id = randomId();
        repository.save(new ApartmentBookingHistory(id));

        assertThat(repository.existFor(id)).isTrue();
    }

    @Test
    @Transactional
    void shouldFindExistingApartmentBookingHistory() {
        apartmentId = randomId();
        LocalDate start = LocalDate.of(2020, 1, 1);
        LocalDate end = LocalDate.of(2020, 1, 10);
        LocalDateTime eventCreationDate = LocalDateTime.now();
        String ownerId = randomId();
        String tenantId = randomId();
        ApartmentBookingHistory apartmentBookingHistory = new ApartmentBookingHistory(apartmentId);
        apartmentBookingHistory.add(ApartmentBooking.start(eventCreationDate, ownerId, tenantId, new BookingPeriod(start, end)));
        repository.save(apartmentBookingHistory);


        ApartmentBookingHistory actual = repository.findFor(apartmentId);

        ApartmentBookingHistoryAssertion.assertThat(actual)
                .hasApartmentBookingsAmount(1)
                .hasApartmentBookingSatisfies(actualBooking -> {
                    ApartmentBookingAssertion.assertThat(actualBooking)
                            .hasOwnerIdEqualTo(ownerId)
                            .hasTenantIdEqualTo(tenantId)
                            .hasBookingPeriodThatHas(start, end);
                });
    }


    private String randomId() {
        return UUID.randomUUID().toString();
    }
}