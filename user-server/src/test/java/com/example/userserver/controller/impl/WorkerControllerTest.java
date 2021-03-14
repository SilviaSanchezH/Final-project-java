package com.example.userserver.controller.impl;

import com.example.userserver.client.CenterClient;
import com.example.userserver.controller.dto.*;
import com.example.userserver.enums.Gender;
import com.example.userserver.model.Client;
import com.example.userserver.model.Worker;
import com.example.userserver.repository.ClientRepository;
import com.example.userserver.repository.WorkerRepository;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class WorkerControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private WorkerRepository workerRepository;

    private MockMvc mockMvc;

    @MockBean
    private CenterClient centerClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Worker pepe;
    private Worker pepa;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        pepe = new Worker("pepe", "987654", "Pepe", "Lopez", "Lopez", "657342187", "Auxiliar", "762", 1L, Gender.MALE);
        pepa = new Worker("pepa", "987654", "Pepa", "Garcia", "Garcia", "65736187", "Enfermero", "762", 1L, Gender.FEMALE);
        workerRepository.saveAll(List.of(pepe,pepa));
    }

    @AfterEach
    void tearDown() {
        workerRepository.deleteAll();
    }

    @Test
    void getAllWorkers_allWorkers() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/workers/")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Pepe"));
        assertTrue(result.getResponse().getContentAsString().contains("Pepa"));
    }

    @Test
    void getWorker_validId_worker() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/worker/" + pepe.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Lopez"));
    }

    @Test
    void getWorker_invalidId_notWorker() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/client/" + pepe.getId()+98)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void addWorker_validWorker_newWorker() throws Exception {
        mockStoreCenterDoNothing();
        WorkerDTO workerDTO = new WorkerDTO("ruben", "987654", "Ruben", "Fernandez", "Lopez", "673542187", 1L, "Auxiliar", "762", "MALE");

        String body = objectMapper.writeValueAsString(workerDTO);
        MvcResult result = this.mockMvc.perform(
                post("/worker")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ruben"))
                .andReturn();
        Worker responseWorker = objectMapper.readValue(result.getResponse().getContentAsString(), Worker.class);
        assertTrue(workerRepository.findById(responseWorker.getId()).isPresent());
    }

    @Test
    void addWorker_invalidCenterId_notNewWorker() throws Exception {
        mockStoreCenterNotFound();
        WorkerDTO workerDTO = new WorkerDTO("ruben", "987654", "Ruben", "Fernandez", "Lopez", "673542187", 1L, "Auxiliar", "762", "MALE");

        String body = objectMapper.writeValueAsString(workerDTO);
        MvcResult result = this.mockMvc.perform(
                post("/worker")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void addWorker_invalidPhone_notNewWorker() throws Exception {
        mockStoreCenterDoNothing();
        WorkerDTO workerDTO = new WorkerDTO("ruben", "987654", "Ruben", "Fernandez", "Lopez", "62187", 1L, "Auxiliar", "762", "MALE");

        String body = objectMapper.writeValueAsString(workerDTO);
        MvcResult result = this.mockMvc.perform(
                post("/worker")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().getMessage().contains("Not valid phone number format"));
    }

    @Test
    void addWorker_invalidPassword_notNewWorker() throws Exception {
        mockStoreCenterDoNothing();
        WorkerDTO workerDTO = new WorkerDTO("ruben", "989", "Ruben", "Fernandez", "Lopez", "621845877", 1L, "Auxiliar", "762", "MALE");

        String body = objectMapper.writeValueAsString(workerDTO);
        MvcResult result = this.mockMvc.perform(
                post("/worker")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().getMessage().contains("Password must be at least 6 characters long"));
    }

    @Test
    void updateWorker_validWorker_updateWorker() throws Exception {
        mockStoreCenterDoNothing();
        UpdateWorkerDTO workerDTO = new UpdateWorkerDTO(pepe.getId(), "ruben", "987654", "Ruben", "Fernandez", "Lopez", "621222287", 1L, "Auxiliar", "762", "MALE");
        String body = objectMapper.writeValueAsString(workerDTO);
        MvcResult result = this.mockMvc.perform(
                put("/worker/" + pepe.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ruben"))
                .andReturn();
        Worker responseWorker = objectMapper.readValue(result.getResponse().getContentAsString(), Worker.class);
        assertTrue(workerRepository.findById(responseWorker.getId()).isPresent());
    }

    @Test
    void updateWorker_invalidCenterId_notUpdateWorker() throws Exception {
        mockStoreCenterNotFound();
        UpdateWorkerDTO workerDTO = new UpdateWorkerDTO(pepe.getId(), "ruben", "987654", "Ruben", "Fernandez", "Lopez", "621222287", 1L, "Auxiliar", "762", "MALE");
        String body = objectMapper.writeValueAsString(workerDTO);
        MvcResult result = this.mockMvc.perform(
                put("/worker/" + pepe.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void updateWorker_invalidWorker_notUpdateWorker() throws Exception {
        mockStoreCenterDoNothing();
        UpdateWorkerDTO workerDTO = new UpdateWorkerDTO(pepe.getId(), "ruben", "987654", "Ruben", "Fernandez", "Lopez", "687", 1L, "Auxiliar", "762", "MALE");
        String body = objectMapper.writeValueAsString(workerDTO);
        MvcResult result = this.mockMvc.perform(
                put("/worker/" + pepe.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void deleteWorker_validId_deleteWorker() throws Exception{
        MvcResult result = this.mockMvc.perform(
                delete("/worker/" + pepe.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(workerRepository.findById(pepe.getId()).isEmpty());
    }

    @Test
    void deleteWorker_invalidId_deleteWorker() throws Exception{
        MvcResult result = this.mockMvc.perform(
                delete("/worker/" + pepe.getId()+65)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void workersByCenterId() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/workers/center/"+ pepe.getCenter())
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();
    }

    @Test
    void workersList() throws Exception {
        List<Long> ids = new ArrayList<>();
        ids.add(pepe.getId());
        ids.add(pepa.getId());
        String body = objectMapper.writeValueAsString(ids);
        MvcResult result = this.mockMvc.perform(
                post("/workers/list")
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