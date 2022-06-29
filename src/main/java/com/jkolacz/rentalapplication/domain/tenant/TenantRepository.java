package com.jkolacz.rentalapplication.domain.tenant;

public interface TenantRepository {
    boolean existById(String tenantId);
}
