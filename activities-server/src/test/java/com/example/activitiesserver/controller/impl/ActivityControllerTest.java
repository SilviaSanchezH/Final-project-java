package com.example.activitiesserver.controller.impl;

import com.example.activitiesserver.controller.client.CenterClient;
import com.example.activitiesserver.controller.client.UserClient;
import com.example.activitiesserver.controller.dto.ActivityDTO;
import com.example.activitiesserver.controller.dto.CenterDTO;
import com.example.activitiesserver.controller.dto.ClientDTO;
import com.example.activitiesserver.controller.dto.WorkerDTO;
import com.example.activitiesserver.enums.ActivityType;
import com.example.activitiesserver.model.Activity;
import com.example.activitiesserver.model.Client;
import com.example.activitiesserver.repository.ActivityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ActivityControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ActivityRepository activityRepository;

    @MockBean
    private UserClient userClient;

    @MockBean
    private CenterClient centerClient;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Activity activity1;
    private Activity activity2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        activity1 = new Activity("Paseo por el campo", "Un precioso paseo", ActivityType.LOCAL_TOUR, LocalDate.of(2021, 3, 25), "7:00 PM", 1L);
        activity2 = new Activity("Viaje a Caracas", "Un precioso viaje", ActivityType.TRIP, LocalDate.of(2021, 3, 25), "7:00 PM", 2L);
        activityRepository.saveAll(List.of(activity1, activity2));
    }

    @AfterEach
    void tearDown() {
        activityRepository.deleteAll();
    }

    @Test
    void getAllActivities_allActivities() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/activities/")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Paseo por el campo"));
        assertTrue(result.getResponse().getContentAsString().contains("Caracas"));
    }

    @Test
    void getActivity_validId_activity() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/activity/" + activity1.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Paseo por el campo"));
    }

    @Test
    void getActivity_invalidId_notActivity() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/activity/" + activity1.getId()+655)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getActivitiesByCenter_validId_activitiesByCenter() throws Exception {
        mockStoreCenterDoNothing();
        MvcResult result = this.mockMvc.perform(
                get("/activities/center/" + activity1.getCenter())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Paseo por el campo"));
    }

    @Test
    void getActivitiesByCenter_invalidId_notActivitiesByCenter() throws Exception {
        mockStoreCenterNotFound();
        MvcResult result = this.mockMvc.perform(
                get("/activities/center/" + activity1.getCenter()+655)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void addWorkerToActivity_validId_addWorker() throws Exception {
        MvcResult result = this.mockMvc.perform(
                post("/activity/"+activity1.getId()+"/worker/1")
        ).andExpect(status().isOk()).andReturn();

        assertEquals(activityRepository.findById(activity1.getId()).get().getWorkers().size(), 1);
        assertEquals(activityRepository.findById(activity1.getId()).get().getWorkers().get(0).getWorkerId(), 1L);
    }

    @Test
    void addWorkerToActivity_invalidId_notAddWorker() throws Exception {
        MvcResult result = this.mockMvc.perform(
                post("/activity/"+activity1.getId()+10+"/worker/1")
        ).andExpect(status().isNotFound()).andReturn();

        assertEquals(activityRepository.findById(activity1.getId()).get().getWorkers().size(), 0);
    }

    @Test
    void addClientToActivity_validId_addClient() throws Exception{
        MvcResult result = this.mockMvc.perform(
                post("/activity/"+activity1.getId()+"/client/1")
        ).andExpect(status().isOk()).andReturn();

        assertEquals(activityRepository.findById(activity1.getId()).get().getClients().size(), 1);
        assertEquals(activityRepository.findById(activity1.getId()).get().getClients().get(0).getClientId(), 1L);
    }

    @Test
    void addClientToActivity_invalidId_notAddClient() throws Exception {
        MvcResult result = this.mockMvc.perform(
                post("/activity/"+activity1.getId()+10+"/client/1")
        ).andExpect(status().isNotFound()).andReturn();

        assertEquals(activityRepository.findById(activity1.getId()).get().getClients().size(), 0);
    }

    @Test
    void getWorkersByActivity_validId_getWorkersByActivity() throws Exception{
        mockUserClientDoNothing();
        MvcResult result = this.mockMvc.perform(
                get("/activity/" + activity1.getId()+"/workers")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("ruben"));
    }

    @Test
    void getWorkersByActivity_invalidId_notGetWorkersByActivity() throws Exception{
        mockUserClientDoNothing();
        MvcResult result = this.mockMvc.perform(
                get("/activity/" + (activity1.getId()+123)+"/workers")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getClientsByActivity_validId_getClientsByActivity() throws Exception{
        mockUserClientDoNothing();
        MvcResult result = this.mockMvc.perform(
                get("/activity/" + activity1.getId()+"/clients")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Mercedes"));
    }

    @Test
    void getClientsByActivity_invalidId_notGetClientsByActivity() throws Exception{
        mockUserClientDoNothing();
        MvcResult result = this.mockMvc.perform(
                get("/activity/" + (activity1.getId()+123)+"/clients")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void addActivity_validActivity_addActivity() throws Exception{
        mockStoreCenterDoNothing();
        ActivityDTO activityDTO = new ActivityDTO("Paseo por el campo", "Un precioso paseo", "LOCAL_TOUR", LocalDate.of(2021, 3, 25), "7:00 PM", 1L, new ArrayList<Long>(), new ArrayList<Long>());

        String body = objectMapper.writeValueAsString(activityDTO);
        MvcResult result = this.mockMvc.perform(
                post("/activity")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Paseo por el campo"))
                .andReturn();
    }

    @Test
    void addActivity_invalidCenter_notAddActivity() throws Exception{
        mockStoreCenterNotFound();
        ActivityDTO activityDTO = new ActivityDTO("Paseo por el campo", "Un precioso paseo", "LOCAL_TOUR", LocalDate.of(2021, 3, 25), "7:00 PM", 3L, new ArrayList<Long>(), new ArrayList<Long>());

        String body = objectMapper.writeValueAsString(activityDTO);
        MvcResult result = this.mockMvc.perform(
                post("/activity")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void updateActivity_validActivity_updateActivity() throws Exception {
        mockStoreCenterDoNothing();
        ActivityDTO activityDTO = new ActivityDTO(activity1.getId(), "Paseo por la pradera", "Un precioso paseo", "LOCAL_TOUR", LocalDate.of(2021, 3, 25), "7:00 PM", 1L, new ArrayList<Long>(), new ArrayList<Long>());

        String body = objectMapper.writeValueAsString(activityDTO);
        MvcResult result = this.mockMvc.perform(
                put("/activity/"+activity1.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Paseo por la pradera"))
                .andReturn();
    }

    @Test
    void updateActivity_invalidActivityId_notUpdateActivity() throws Exception {
        mockStoreCenterDoNothing();
        ActivityDTO activityDTO = new ActivityDTO(activity1.getId()+123, "Paseo por la pradera", "Un precioso paseo", "LOCAL_TOUR", LocalDate.of(2021, 3, 25), "7:00 PM", 1L, new ArrayList<Long>(), new ArrayList<Long>());

        String body = objectMapper.writeValueAsString(activityDTO);
        MvcResult result = this.mockMvc.perform(
                put("/activity/"+activity1.getId()+123)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void updateActivity_invalidCenterId_notUpdateActivity() throws Exception {
        mockStoreCenterNotFound();
        ActivityDTO activityDTO = new ActivityDTO(activity1.getId(), "Paseo por la pradera", "Un precioso paseo", "LOCAL_TOUR", LocalDate.of(2021, 3, 25), "7:00 PM", 1L, new ArrayList<Long>(), new ArrayList<Long>());

        String body = objectMapper.writeValueAsString(activityDTO);
        MvcResult result = this.mockMvc.perform(
                put("/activity/"+activity1.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void deleteActivity_validActivityId_deleteActivity() throws Exception{
        MvcResult result = this.mockMvc.perform(
                delete("/activity/"+activity1.getId())
        ).andExpect(status().isOk()).andReturn();

        assertFalse(activityRepository.findById(activity1.getId()).isPresent());
    }

    @Test
    void deleteActivity_invalidActivityId_notDeleteActivity() throws Exception{
        MvcResult result = this.mockMvc.perform(
                delete("/activity/"+activity1.getId()+123)
        ).andExpect(status().isNotFound()).andReturn();
    }

    private void mockStoreCenterDoNothing() {
        Mockito.doReturn(new CenterDTO(1L, "Centro de mayores Arbolitos", "Plaza mayor, 1", "Madrid", "987654322")).when(centerClient).getCenter(anyLong());
    }

    private void mockStoreCenterNotFound() {
        Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(centerClient).getCenter(anyLong());
    }

    private void mockUserClientDoNothing() {
        List<WorkerDTO> workerDTOList = new ArrayList<WorkerDTO>();
        List<ClientDTO> clientDTOList = new ArrayList<ClientDTO>();
        workerDTOList.add(new WorkerDTO(1L, "ruben", "", "Ruben", "Lopez", "Uwu", "666555444", "Auxiliar", "762", "MALE"));
        clientDTOList.add(new ClientDTO(1L, "Mercedes", "98", "Mercedes", "Heras", "Alonso", "987574721", 1L, "Calle Manuel", "Madrid", "FEMALE", 89));
        Mockito.doReturn(clientDTOList).when(userClient).clientsList(anyList());
        Mockito.doReturn(workerDTOList).when(userClient).workersList(anyList());
    }
}