package com.jkolacz.rentalapplication.domain.agreement;

import java.util.UUID;

public interface AgreementRepository {
    void save(Agreement agreement);

    Agreement findById(UUID agreementId);
}
