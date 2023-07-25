package me.kopkaj.wuthichai.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
public class TableControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testInitializeTables() throws Exception {
        String requestJson = "{\"capacity\": 10}";

        MockHttpServletRequestBuilder request = post("/restaurant/init")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"message\":\"OK\"}"));
    }

    @Test
    public void testReserveTables() throws Exception {
        String requestJson = "{\"guestNumber\": 7}";

        MockHttpServletRequestBuilder request = post("/restaurant/reserve")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.reservationId").exists())
                .andExpect(content().string("{\"reservationId\":1}"));;
    }

    @Test
    public void testCancelReservation() throws Exception {
        String requestJson = "{\"reservationId\": 1}";

        MockHttpServletRequestBuilder request = post("/restaurant/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"message\":\"OK\"}"));
    }
}
