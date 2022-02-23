package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartment;

import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaApartmentRepositoryIntegrationTest {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Test
    void shouldThrowExceptionWhenApartmentDoesNotExist() {
        ApartmentNotFoundException actual = assertThrows(ApartmentNotFoundException.class, () -> apartmentRepository.findById("1234"));

        assertThat(actual).hasMessage("Apartment with id 1234 does not exist");
    }
}