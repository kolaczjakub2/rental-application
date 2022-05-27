package com.jkolacz.rentalapplication.infrastructure.rest.api.hotel;

import com.jkolacz.rentalapplication.application.hotel.HotelDto;
import com.jkolacz.rentalapplication.infrastructure.json.JsonFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("SystemTest")
class HotelRestControllerSystemTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldReturnNothingWhenThereWasNoHotelCreated() throws Exception {
        mockMvc.perform(get("/hotel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(0)));
    }

    @Test
    void shouldReturnExistingHotels() throws Exception {
        HotelDto hotel1 = new HotelDto("Big Hotel", "Florianska", "13", "12-345", "Cracow", "Poland");
        HotelDto hotel2 = new HotelDto("Bigger Hotel", "Florianska", "43", "12-345", "Cracow", "Poland");

        addHotel(hotel1);
        addHotel(hotel2);

        mockMvc.perform(get("/hotel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", hasSize(2)))
                .andExpect(jsonPath("$.[0].name").value("Big Hotel"))
                .andExpect(jsonPath("$.[0].buildingNumber").value("13"))
                .andExpect(jsonPath("$.[1].name").value("Bigger Hotel"))
                .andExpect(jsonPath("$.[1].buildingNumber").value("43"));
        ;
    }

    private void addHotel(HotelDto hotelDto) throws Exception {
        mockMvc.perform(post("/hotel").contentType(MediaType.APPLICATION_JSON).content(new JsonFactory().create(hotelDto)))
                .andExpect(status().isCreated());
    }
}