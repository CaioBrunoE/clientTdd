package com.ms.tdd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.tdd.TddApplicationTests;
import com.ms.tdd.dto.ClientDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientControllerTests extends TddApplicationTests {

    private MockMvc mockMvc;

    private ClientDTO clientDTO = new ClientDTO("65f052c545cd1a4513379e14","Neuber", "neuber.paiva@gmail.com", "9994545429", "440120165656");;

    @Autowired
    private ClientController controller;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @Order(1)
    public void testCreateClient() throws Exception {
        this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    @Order(2)
    public void testFindAllClient() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/clients"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    @Order(3)
    public void testFindByIdClient() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/clients/" + clientDTO.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());;
    }

    @Test
    @Order(4)
    public void testUpdateClient() throws Exception {
        this.mockMvc.perform( MockMvcRequestBuilders
                        .put("/api/clients/" + clientDTO.getId())
                        .content(asJsonString(new ClientDTO(null, "Maria", "maria.p@gmail.com", "24394545429", "440120165656")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    @Order(5)
    public void testDeleteClient() throws Exception {
        this.mockMvc.perform( MockMvcRequestBuilders
                        .delete("/api/clients/" + clientDTO.getId())
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
