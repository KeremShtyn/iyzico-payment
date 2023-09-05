package com.iyzico.challenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iyzico.challenge.Application;
import com.iyzico.challenge.dto.SeatDTO;
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
@Tag("SeatControllerTest")
public class SeatControllerIntegrationTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser("spring")
    public void test_setSeat_OK() throws Exception {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setAvailable(true);
        seatDTO.setId(1L);
        seatDTO.setFlightId(1L);
        seatDTO.setSeatNumber("123");
        seatDTO.setFlightName("Istanbul");
        seatDTO.setPrice(BigDecimal.valueOf(120.0));

        mockMvc.perform(post("/api/v1/seat")
                        .content(objectMapper.writeValueAsString(seatDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser("spring")
    public void test_setSeat_BAD() throws Exception {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setAvailable(true);
        seatDTO.setId(1L);
        seatDTO.setFlightId(1L);
        seatDTO.setSeatNumber("");
        seatDTO.setFlightName("");
        seatDTO.setPrice(BigDecimal.valueOf(120.0));

        mockMvc.perform(post("/api/v1/seat")
                        .content(objectMapper.writeValueAsString(seatDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
}
