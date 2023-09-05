package com.iyzico.challenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iyzico.challenge.Application;
import com.iyzico.challenge.dto.BookingDTO;
import org.junit.Before;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Tag("BookingControllerTest")
public class BookingControllerIntegrationTest {


    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser("spring")
    public void test_BookSeat_OK() throws Exception {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setSeatId(1L);
        bookingDTO.setId(1L);
        bookingDTO.setFlightId(1L);
        bookingDTO.setSeatNumber("123");
        bookingDTO.setFlightName("Istanbul");
        bookingDTO.setTotalPrice(BigDecimal.valueOf(120.0));
        bookingDTO.setSeatId(1L);


        mockMvc.perform(post("/api/v1/booking")
                        .content(objectMapper.writeValueAsString(bookingDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }


    @Test
    @WithMockUser("spring")
    public void test_BookSeat_BAD() throws Exception {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setSeatId(1L);
        bookingDTO.setId(1L);
        bookingDTO.setFlightId(1L);
        bookingDTO.setSeatNumber("");
        bookingDTO.setFlightName("");
        bookingDTO.setTotalPrice(BigDecimal.valueOf(120.0));
        bookingDTO.setSeatId(1L);


        mockMvc.perform(post("/api/v1/booking")
                        .content(objectMapper.writeValueAsString(bookingDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

}
