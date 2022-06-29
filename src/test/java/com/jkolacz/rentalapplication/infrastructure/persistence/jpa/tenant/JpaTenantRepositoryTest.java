package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.tenant;

import com.jkolacz.rentalapplication.domain.tenant.TenantRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class JpaTenantRepositoryTest {
    private final TenantRepository repository = new JpaTenantRepository();

    @Test
    void shouldAlwaysReturnTrue() {
        assertThat(repository.existById(UUID.randomUUID().toString())).isTrue();
    }
}