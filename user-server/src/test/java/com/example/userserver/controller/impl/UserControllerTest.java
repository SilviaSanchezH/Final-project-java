package com.example.userserver.controller.impl;

import com.example.userserver.enums.Gender;
import com.example.userserver.model.User;
import com.example.userserver.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;


    private ObjectMapper objectMapper = new ObjectMapper();

    private User paco;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        paco= new User(1L, "willy", "lopez", "123456", "William", "Sanchez", "987456321", 1L, Gender.MALE);
        userRepository.save(paco);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void getUser_validUsername_User() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/user/" + paco.getUsername())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("William"));
    }

    @Test
    void getUser_invalidUsername_User() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/user/" + "owo")
        ).andExpect(status().isNotFound()).andReturn();
    }
}