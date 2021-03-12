package com.example.centerserver.controller.impl;

import com.example.centerserver.model.Center;
import com.example.centerserver.repository.CenterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CenterControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CenterRepository centerRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Center center1;
    private Center center2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        center1 = new Center("Centro rios", "Calle central, 8", "Madrid", "987456321");
        center2 = new Center("Mi centrito", "Calle miguelañez, 8", "Madrid", "987278321");

        centerRepository.saveAll(List.of(center1, center2));
    }

    @AfterEach
    void tearDown() {
        centerRepository.deleteAll();
    }

    @Test
    void getAllCenters_allCenters() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/centers/")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Centro rios"));
        assertTrue(result.getResponse().getContentAsString().contains("Mi centrito"));
    }

    @Test
    void getCenter_validCenterId_getCenter() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/center/" + center1.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Centro rios"));
    }

    @Test
    void getCenter_invalidCenterId_notGetCenter() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/center/" + center1.getId()+645)
        ).andExpect(status().isNotFound()).andReturn();

    }
}