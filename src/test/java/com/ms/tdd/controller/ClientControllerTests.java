package com.ms.tdd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.tdd.TddApplicationTests;
import com.ms.tdd.dto.ClientDTO;
import com.ms.tdd.model.Client;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@SpringBootTest
@ActiveProfiles("test")
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
    public void testCreateClient() throws Exception {

        ClientDTO newClient = new ClientDTO(null,"Neuber", "neuber.paiva@gmail.com", "9994545429", "440120165656");

        this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newClient))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    @Order(1)
    public void testFindAllClient() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/clients"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }



    @Test
    @Order(2)
    public void testFindByIdClient() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/clients/65ef877411143e17256b9703"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());;

    }

    @Test
    @Order(3)
    public void testUpdateClient() throws Exception {
        this.mockMvc.perform( MockMvcRequestBuilders
                        .put("/api/clients/65ef877411143e17256b9703")
                        .content(asJsonString(new ClientDTO(null, "Maria", "maria.p@gmail.com", "24394545429", "440120165656")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    @Order(4)
    public void testDeleteClient() throws Exception {
        ClientDTO newClientDto = new ClientDTO(null, "Neuber", "neuber.paiva@gmail.com", "9994545429", "440120165656");

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newClientDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(response);
        String clientId = jsonObject.getString("id");

        this.mockMvc.perform( MockMvcRequestBuilders
                        .delete("/api/clients/" + clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
