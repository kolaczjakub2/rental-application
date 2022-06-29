package com.jkolacz.rentalapplication.application.apartment;

import com.jkolacz.rentalapplication.domain.address.AddressCatalogue;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentDomainService;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentEventsPublisher;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentFactory;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;
import com.jkolacz.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;
import com.jkolacz.rentalapplication.domain.booking.BookingRepository;
import com.jkolacz.rentalapplication.domain.clock.Clock;
import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import com.jkolacz.rentalapplication.domain.owner.OwnerRepository;
import com.jkolacz.rentalapplication.domain.tenant.TenantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApartmentApplicationServiceFactory {
    @Bean
    @SuppressWarnings("checkstyle:ParameterNumber")
    ApartmentApplicationService apartmentApplicationService(
            ApartmentRepository apartmentRepository, BookingRepository bookingRepository, OwnerRepository ownerRepository,
            TenantRepository tenantRepository, ApartmentOfferRepository apartmentOfferRepository, AddressCatalogue addressCatalogue,
            EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        ApartmentEventsPublisher apartmentEventsPublisher = new ApartmentEventsPublisher(eventIdFactory, clock, eventChannel);
        ApartmentFactory apartmentFactory = new ApartmentFactory(ownerRepository, addressCatalogue);
        ApartmentDomainService apartmentDomainService = new ApartmentDomainService(
                apartmentRepository, apartmentOfferRepository, bookingRepository, tenantRepository, apartmentEventsPublisher);

        return new ApartmentApplicationService(apartmentRepository, bookingRepository, apartmentFactory, apartmentDomainService);
    }
}
