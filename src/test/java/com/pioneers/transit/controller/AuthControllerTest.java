package com.pioneers.transit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pioneers.transit.dto.request.AuthRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvc.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void register() throws Exception {
        AuthRequest request = new AuthRequest();
        request.setEmail("user3@gmail.com");
        request.setPassword("12345");

        mockMvc.perform(
                post("/auth/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isOk());
    }

    @Test
    void registerBadRequest() throws Exception {
        AuthRequest request = new AuthRequest();
        request.setEmail("");
        request.setPassword("");

        mockMvc.perform(
                post("/auth/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest());//email dan passwordnya tidak ada
    }

    @Test
    void registerAdmin() throws Exception {
        AuthRequest request = new AuthRequest();
        request.setEmail("admin4@gmail.com");
        request.setPassword("12345");

        mockMvc.perform(
                post("/auth/register/admin")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isOk());
    }

    @Test
    void registerAdminBadRequest() throws Exception {
        AuthRequest request = new AuthRequest();
        request.setEmail("");
        request.setPassword("");

        mockMvc.perform(
                post("/auth/register/admin")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest());//email dan passwordnya tidak ada
    }

    @Test
    void login() throws Exception {
        String email = "superadmin@gmail.com";
        String password = "12345";

        String body = "{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}";

        MvcResult result = mockMvc.perform(
                post("/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        ).andExpect(status().isOk()).andReturn();
        String token = result.getResponse().getContentAsString();
        log.info("TOKEN " + token);
    }
    @Test
    void loginFailed() throws Exception {
        String email = "superadmin@gmail.com";
        String password = "54321";

        String body = "{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}";

        mockMvc.perform(
                post("/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        ).andExpect(status().isForbidden());//password yang dimasukkan salah
    }
}