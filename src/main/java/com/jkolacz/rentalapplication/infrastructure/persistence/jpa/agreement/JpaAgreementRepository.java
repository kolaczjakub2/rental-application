package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.agreement;

import com.jkolacz.rentalapplication.domain.agreement.Agreement;
import com.jkolacz.rentalapplication.domain.agreement.AgreementRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaAgreementRepository implements AgreementRepository {
    @Override
    public void save(Agreement agreement) {

    }

    @Override
    public Agreement findById(UUID agreementId) {
        return null;
    }
}
