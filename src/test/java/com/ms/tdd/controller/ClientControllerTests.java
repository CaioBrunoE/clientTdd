package com.ms.tdd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.tdd.TddApplicationTests;
import com.ms.tdd.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ClientControllerTests extends TddApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private ClientController controller;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @Order(0)
    public void testClientList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/clients"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    @Order(1)
    public void testCreateClient() throws Exception
    {
        this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/clients")
                        .content(asJsonString(new Client(null, "Neuber", "neuber.paiva@gmail.com", "9994545429", "440120165656")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
