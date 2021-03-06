package com.jkolacz.rentalapplication.infrastructure.addressservice;

import com.jkolacz.rentalapplication.domain.address.AddressCatalogue;
import com.jkolacz.rentalapplication.domain.address.AddressDto;
import org.springframework.web.client.RestTemplate;

class RestAddressCatalogueClient implements AddressCatalogue {
    private final RestTemplate restTemplate;
    private final String url;

    RestAddressCatalogueClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public boolean exists(AddressDto addressDto) {
        AddressVerification verification = restTemplate.postForObject(url + "/address/verify", addressDto, AddressVerification.class);

        return verification.isValid();
    }
}
