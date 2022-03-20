package com.jkolacz.rentalapplication.infrastructure.rest.api.apartment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ApartmentRestControllerTest {

    private static final String OWNER_ID = "1234";
    private static final String STREET = "Florianska";
    private static final String POSTAL_CODE = "12-345";
    private static final String HOUSE_NUMBER = "1";
    private static final String APARTMENT_NUMBER = "13";
    private static final String CITY = "Cracow";
    private static final String COUNTRY = "Poland";
    private static final String DESCRIPTION = "Nice place to stay";
    private static final Map<String, Double> ROOM_DEFINITION = ImmutableMap.of(
            "Toilet", 10.0, "Bedroom", 30.0
    );

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnNothingWhenApartmentDoesNotExist() throws Exception {
        String notExistingId = UUID.randomUUID().toString();


        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/apartment/" + notExistingId));

        actual.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.apartment").isEmpty())
                .andExpect(jsonPath("$.bookingHistory").isEmpty());
    }


    @Test
    void shouldReturnExistingApartment() throws Exception {

        ApartmentDto apartmentDto = new ApartmentDto(OWNER_ID, STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY,
                DESCRIPTION, ROOM_DEFINITION);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/apartment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(apartmentDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String existingId;


        mockMvc.perform(get(mvcResult.getResponse().getRedirectedUrl()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.apartment.ownerId").value(OWNER_ID))
                .andExpect(jsonPath("$.apartment.street").value(STREET))
                .andExpect(jsonPath("$.apartment.postalCode").value(POSTAL_CODE))
                .andExpect(jsonPath("$.apartment.houseNumber").value(HOUSE_NUMBER))
                .andExpect(jsonPath("$.bookingHistory").isEmpty());
    }

    private String asJson(ApartmentDto apartmentDto) {
        try {
            return new ObjectMapper().writeValueAsString(apartmentDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}