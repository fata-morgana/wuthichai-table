package me.kopkaj.wuthichai.controller;

import me.kopkaj.wuthichai.configuration.TableConfiguration;
import me.kopkaj.wuthichai.service.TableService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TableController.class)
@ContextConfiguration(classes = {TableConfiguration.class})
public class TableControllerTest {
    private MockMvc mockMvc;

    private TableService tableService = mock(TableService.class);

    private TableController tableController = new TableController(tableService);

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(tableController).build();
    }

    @Test
    public void testInitializeTables() throws Exception {
        String requestJson = "{\"capacity\": 10}";

        mockMvc.perform(MockMvcRequestBuilders.post("/restaurant/init")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"message\":\"OK\"}"));

        verify(tableService, times(1)).initializeTables(10);
    }

    @Test
    public void testReserveTables() throws Exception {
        String requestJson = "{\"guestNumber\": 7}";
        String responseJson = "{\"reservationId\":1}";

        when(tableService.makeReservation(7)).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/restaurant/reserve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string(responseJson));

        verify(tableService, times(1)).makeReservation(7);
    }

    @Test
    public void testCancelReservation() throws Exception {
        String requestJson = "{\"reservationId\": 1}";

        doNothing().when(tableService).cancelReservation(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/restaurant/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"message\":\"OK\"}"));

        verify(tableService, times(1)).cancelReservation(1);
    }
}
