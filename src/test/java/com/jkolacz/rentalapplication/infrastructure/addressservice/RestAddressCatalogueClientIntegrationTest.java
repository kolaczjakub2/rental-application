//package com.jkolacz.rentalapplication.infrastructure.addressservice;
//
//import com.jkolacz.rentalapplication.addressservice.addressverifiacation.AddressVerificationContract;
//import com.jkolacz.rentalapplication.addressservice.addressverifiacation.AddressVerificationRequest;
//import com.jkolacz.rentalapplication.addressservice.addressverifiacation.AddressVerificationScenario;
//import com.jkolacz.rentalapplication.domain.address.AddressCatalogue;
//import com.jkolacz.rentalapplication.domain.address.AddressDto;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Tag("IntegrationTest")
//class RestAddressCatalogueClientIntegrationTest {
//    private final AddressVerificationContract contract = new AddressVerificationContract();
//    @Autowired private AddressCatalogue addressCatalogue;
//
//    @Test
//    void shouldRecognizeValidAddress() {
//        AddressVerificationScenario scenario = contract.validAddress();
//        AddressDto addressDto = addressDto(scenario);
//
//        boolean actual = addressCatalogue.exists(addressDto);
//
//        assertThat(actual).isTrue();
//    }
//
//    @Test
//    void shouldRecognizeInvalidAddress() {
//        AddressVerificationScenario scenario = contract.invalidAddress();
//        AddressDto addressDto = addressDto(scenario);
//
//        boolean actual = addressCatalogue.exists(addressDto);
//
//        assertThat(actual).isFalse();
//    }
//
//    private AddressDto addressDto(AddressVerificationScenario scenario) {
//        AddressVerificationRequest request = scenario.getRequest();
//        return new AddressDto(request.getStreet(), request.getPostalCode(), request.getBuildingNumber(), request.getCity(), request.getCountry());
//    }
//}