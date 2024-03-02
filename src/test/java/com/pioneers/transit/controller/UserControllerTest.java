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
import org.springframework.http.HttpHeaders;
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
    private String token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9." +
            "eyJpc3MiOiJ0cmFucy1pdCIsInN1YiI6ImZlNmJhZWU5LTNjM2E" +
            "tNDg2Ny04NThmLWQ3MGM0NmRmOGQxZCIsImV4cCI6MTcwOTUzODk2" +
            "MSwicm9sZXMiOlsiUk9MRV9DVVNUT01FUiJdfQ.rc2Wbrmcvt92w" +
            "uKjiwRULJSr3BgWH_j2KRoZmUcUuiBJGN9x6xNzsj8G_cDwK7pYe2WoNIs46M3af-MYDL1wpQ";

//    @BeforeEach
//    void setUp() {
//        logRepository.deleteAll();
//        purchaseRepository.deleteAll();
//        userRepository.deleteAll();
//    }

    @Test
    void createUserSuccess() throws Exception {
//        String email = "superadmin@gmail.com";
//        String password = "12345";
//
//        String body = "{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}";
//
//        MvcResult result = mockMvc.perform(
//                post("/auth/login")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(body)
//                ).andExpect(status().isOk()).andReturn();
//
//
//        String response = result.getResponse().getContentAsString();
//        log.info("testtt " + token);

//        UserCredential userCredential = userCredentialRepository.findById("fe6baee9-3c3a-4867-858f-d70c46df8d1d")
//                .orElseThrow(null);
//        UserRequest request = new UserRequest();
//        request.setUsername("chiteki");
//        request.setName("Rizki");
//        request.setBirthDate(Date.valueOf("2222-11-1"));
//        request.setAddress("Jalan");
//        request.setPhoneNumber("808010102020");
//        request.setUserCredential(userCredential);
//        String json = objectMapper.writeValueAsString(request);
//        log.info("CHITEKI " + json);

        String json = """
                {
                  "username": "chiteki1",
                  "name": "Rizki1",
                  "birthDate" : "2222-11-5",
                  "address": "jalan aja",
                  "phoneNumber": "080809090101",
                  "userCredential": {
                    "id": "fe6baee9-3c3a-4867-858f-d70c46df8d1d"
                  }
                }
                """;

        mockMvc.perform(
                post("/user")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpectAll(
                status().isCreated()
        );
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(
                get("/user")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(
                get("/user/8a15d8a8-34ae-4a5a-bf7f-60d880c02d9e")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void updateUser() throws Exception {
        String json = """
                {
                   "id": "8a15d8a8-34ae-4a5a-bf7f-60d880c02d9e",
                   "username": "chiteki2",
                   "name": "Rizki2",
                   "birthDate" : "2222-11-5",
                   "address": "jalan aja",
                   "phoneNumber": "080809090101"
                 }
                """;

        mockMvc.perform(
                put("/user")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpectAll(
                status().isCreated()
        );
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(
                get("/user/8a15d8a8-34ae-4a5a-bf7f-60d880c02d9e")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }
}