package com.iyzico.challenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iyzico.challenge.Application;
import com.iyzico.challenge.dto.FlightDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Tag("FlightControllerTest")
public class FlightControllerIntegrationTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser("spring")
    public void test_SetFlight_OK() throws Exception {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(1L);
        flightDTO.setDescription("London - Istanbul");
        flightDTO.setName("Istanbul");


        mockMvc.perform(post("/api/v1/flight")
                        .content(objectMapper.writeValueAsString(flightDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("spring")
    public void test_SetFlight_BAD() throws Exception {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(1L);
        flightDTO.setDescription("");
        flightDTO.setName("");


        mockMvc.perform(post("/api/v1/flight")
                        .content(objectMapper.writeValueAsString(flightDTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }




}
