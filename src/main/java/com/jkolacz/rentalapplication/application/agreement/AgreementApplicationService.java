package com.jkolacz.rentalapplication.application.agreement;

import com.jkolacz.rentalapplication.domain.agreement.Agreement;
import com.jkolacz.rentalapplication.domain.agreement.AgreementEventsPublisher;
import com.jkolacz.rentalapplication.domain.agreement.AgreementRepository;

import java.util.UUID;

public class AgreementApplicationService {
    private final AgreementRepository agreementRepository;
    private final AgreementEventsPublisher agreementEventsPublisher;

    public AgreementApplicationService(AgreementRepository agreementRepository, AgreementEventsPublisher agreementEventsPublisher) {
        this.agreementRepository = agreementRepository;
        this.agreementEventsPublisher = agreementEventsPublisher;
    }

    void accept(UUID agreementId) {
        Agreement agreement = agreementRepository.findById(agreementId);

        agreement.accept(agreementEventsPublisher);
    }
}
