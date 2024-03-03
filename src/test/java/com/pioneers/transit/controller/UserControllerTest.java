package com.pioneers.transit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pioneers.transit.dto.request.UserRequest;
import com.pioneers.transit.entity.UserCredential;
import com.pioneers.transit.repository.LogRepository;
import com.pioneers.transit.repository.PurchaseRepository;
import com.pioneers.transit.repository.UserCredentialRepository;
import com.pioneers.transit.repository.UserRepository;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
    private String userId = "9fed65fd-89ff-4efd-af9a-9aac78360a58";
    private String userCredentialId = "ef04a6bc-0a0d-49b6-af1c-a4010c25b123";

//    @BeforeEach
//    void setUp() throws Exception {
//        logRepository.deleteAll();
//        purchaseRepository.deleteAll();
//        userRepository.deleteAll();
//    }

    @Test
    void createUserSuccess() throws Exception {
//        UserCredential userCredential = userCredentialRepository.findById("756cffb2-cfe4-41a8-81ed-b68124e7c280")
//                .orElseThrow(null);
//        UserRequest request = new UserRequest();
//        request.setUsername("chiteki");
//        request.setName("Rizki");
//        request.setBirthDate(Date.valueOf("2200-1-1"));
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
                    "id": "%s"
                  }
                }
                """.formatted(userCredentialId);

        mockMvc.perform(
                post("/user")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
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
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void getById() throws Exception {

        mockMvc.perform(
                get("/user/"+ userId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void updateUser() throws Exception {
        String json = """
                {
                   "id": "%s",
                   "username": "chiteki2",
                   "name": "Rizki2",
                   "birthDate" : "2222-11-5",
                   "address": "jalan aja",
                   "phoneNumber": "080809090101"
                 }
                """.formatted(userId);

        mockMvc.perform(
                put("/user")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
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
                delete("/user/"+ userId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }
}