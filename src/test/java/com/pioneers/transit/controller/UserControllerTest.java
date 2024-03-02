package com.pioneers.transit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pioneers.transit.dto.request.UserRequest;
import com.pioneers.transit.dto.response.ControllerResponse;
import com.pioneers.transit.dto.response.UserResponse;
import com.pioneers.transit.entity.UserCredential;
import com.pioneers.transit.repository.LogRepository;
import com.pioneers.transit.repository.PurchaseRepository;
import com.pioneers.transit.repository.UserCredentialRepository;
import com.pioneers.transit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Date;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.MockMvc.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        logRepository.deleteAll();
        purchaseRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void createUserSuccess() throws Exception {
        String email = "superadmin@gmail.com";
        String password = "12345";

        String body = "{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}";

        MvcResult result = mockMvc.perform(
                post("/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                ).andExpect(status().isOk()).andReturn();


        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"data\": \"", "");
        String token = response.replace("\"}", "");
        log.info("testtt " + token);

        UserCredential userCredential = userCredentialRepository.findById("fe6baee9-3c3a-4867-858f-d70c46df8d1d")
                .git orElseThrow(null);
        UserRequest request = new UserRequest();
        request.setUsername("chiteki");
        request.setName("Rizki");
        request.setBirthDate(Date.valueOf("2222-11-1"));
        request.setAddress("Jalan");
        request.setPhoneNumber("808010102020");
        request.setUserCredential(userCredential);

        mockMvc.perform(
                post("/user")
                        .header("Authorization",  token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteById() {
    }
}