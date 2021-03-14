package com.example.userserver.controller.impl;

import com.example.userserver.client.CenterClient;
import com.example.userserver.controller.dto.CenterDTO;
import com.example.userserver.controller.dto.ClientDTO;
import com.example.userserver.controller.dto.UpdateClientDTO;
import com.example.userserver.enums.Gender;
import com.example.userserver.model.Client;
import com.example.userserver.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ClientControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ClientRepository clientRepository;

    private MockMvc mockMvc;

    @MockBean
    private CenterClient centerClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Client paco;
    private Client paca;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        paca = new Client("paca", "123456", "paca", "sanchez", "garcia", "635214598", "calle central", "madrid", 1L, Gender.FEMALE, 70);
        paco = new Client("paco", "123456", "paco", "lopez", "aguado", "635214594", "calle central", "madrid", 1L, Gender.MALE, 70);
        clientRepository.saveAll(List.of(paca, paco));
    }

    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    void getAllClients_allClients() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/clients/")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("paco"));
        assertTrue(result.getResponse().getContentAsString().contains("paca"));
    }

    @Test
    void getClient_validId_client() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/client/" + paca.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("sanchez"));
    }


    @Test
    void getClient_invalidId_notClient() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/client/" + paca.getId() + 5)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void addClient_validClient_newClient() throws Exception {
        mockStoreCenterDoNothing();
        ClientDTO clientDTO = new ClientDTO("Mercedes", "987654", "Mercedes", "Heras", "Alonso", "987654321", 1L, "Calle Manuel", "Madrid", "FEMALE", 89);
        String body = objectMapper.writeValueAsString(clientDTO);
        MvcResult result = this.mockMvc.perform(
                post("/client")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Mercedes"))
                .andReturn();
        Client responseClient = objectMapper.readValue(result.getResponse().getContentAsString(), Client.class);
        assertTrue(clientRepository.findById(responseClient.getId()).isPresent());
    }

    @Test
    void addClient_invalidCenterId_notNewClient() throws Exception {
        mockStoreCenterNotFound();
        ClientDTO clientDTO = new ClientDTO("Mercedes", "987654", "Mercedes", "Heras", "Alonso", "987654321", 1L, "Calle Manuel", "Madrid", "FEMALE", 89);
        String body = objectMapper.writeValueAsString(clientDTO);
        MvcResult result = this.mockMvc.perform(
                post("/client")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void addClient_invalidPhone_notNewClient() throws Exception {
        mockStoreCenterDoNothing();
        ClientDTO clientDTO = new ClientDTO("Mercedes", "987654", "Mercedes", "Heras", "Alonso", "98721", 1L, "Calle Manuel", "Madrid", "FEMALE", 89);
        String body = objectMapper.writeValueAsString(clientDTO);
        MvcResult result = this.mockMvc.perform(
                post("/client")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().getMessage().contains("Not valid phone number format"));
    }

    @Test
    void addClient_invalidPassword_notNewClient() throws Exception {
        mockStoreCenterDoNothing();
        ClientDTO clientDTO = new ClientDTO("Mercedes", "98", "Mercedes", "Heras", "Alonso", "987574721", 1L, "Calle Manuel", "Madrid", "FEMALE", 89);
        String body = objectMapper.writeValueAsString(clientDTO);
        MvcResult result = this.mockMvc.perform(
                post("/client")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().getMessage().contains("Password must be at least 6 characters long"));
    }

    @Test
    void updateClient_validClient_updateClient() throws Exception{
        mockStoreCenterDoNothing();
        UpdateClientDTO clientDTO = new UpdateClientDTO(paca.getId(), "Mercedes", "987654541", "Mercedes", "Heras", "Alonso", "987654321", 1L, "Calle Manuel", "Madrid", "FEMALE", 89);
        String body = objectMapper.writeValueAsString(clientDTO);
        MvcResult result = this.mockMvc.perform(
                put("/client/" + paca.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mercedes"))
                .andReturn();
        Client responseClient = objectMapper.readValue(result.getResponse().getContentAsString(), Client.class);
        assertTrue(clientRepository.findById(responseClient.getId()).isPresent());
    }

    @Test
    void updateClient_invalidCenterId_notUpdateClient() throws Exception{
        mockStoreCenterNotFound();
        UpdateClientDTO clientDTO = new UpdateClientDTO(paca.getId(), "Mercedes", "987654541", "Mercedes", "Heras", "Alonso", "987654321", 1L, "Calle Manuel", "Madrid", "FEMALE", 89);
        String body = objectMapper.writeValueAsString(clientDTO);
        MvcResult result = this.mockMvc.perform(
                put("/client/" + paca.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void updateClient_invalidClient_notUpdateClient() throws Exception{
        mockStoreCenterDoNothing();
        UpdateClientDTO clientDTO = new UpdateClientDTO(paca.getId(), "Mercedes", "454551", "Mercedes", "Heras", "Alonso", "9321", 1L, "Calle Manuel", "Madrid", "FEMALE", 89);
        String body = objectMapper.writeValueAsString(clientDTO);
        MvcResult result = this.mockMvc.perform(
                put("/client/" + paca.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void deleteClient_validId_deleteClient() throws Exception {
        MvcResult result = this.mockMvc.perform(
                delete("/client/" + paco.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(clientRepository.findById(paco.getId()).isEmpty());
    }

    @Test
    void deleteClient_invalidId_notDeleteClient() throws Exception {
        MvcResult result = this.mockMvc.perform(
                delete("/client/" + paco.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(clientRepository.findById(paco.getId()).isEmpty());
    }

    @Test
    void clientsByCenterId_validId_GetAllClients() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/clients/center/"+ paca.getCenter())
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();
    }


    @Test
    void clientsList() throws Exception {
        List<Long> ids = new ArrayList<>();
        ids.add(paca.getId());
        ids.add(paco.getId());
        String body = objectMapper.writeValueAsString(ids);
        MvcResult result = this.mockMvc.perform(
                post("/clients/list")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();
    }


    private void mockStoreCenterDoNothing() {
        Mockito.doReturn(new CenterDTO(1L, "Centro de mayores Arbolitos", "Plaza mayor, 1", "Madrid", "987654322")).when(centerClient).getCenter(anyLong());
    }

    private void mockStoreCenterNotFound() {
        Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(centerClient).getCenter(anyLong());
    }
}