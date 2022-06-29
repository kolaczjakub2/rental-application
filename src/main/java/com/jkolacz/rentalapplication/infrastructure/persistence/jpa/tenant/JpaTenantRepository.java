package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.tenant;

import com.jkolacz.rentalapplication.domain.tenant.TenantRepository;
import org.springframework.stereotype.Repository;

@Repository
class JpaTenantRepository implements TenantRepository {
    @Override
    public boolean existById(String tenantId) {
        return true;
    }
}
