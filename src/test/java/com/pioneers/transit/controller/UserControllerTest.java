package com.pioneers.transit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pioneers.transit.repository.LogRepository;
import com.pioneers.transit.repository.PurchaseRepository;
import com.pioneers.transit.repository.UserCredentialRepository;
import com.pioneers.transit.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    private String userCredentialId = "756cffb2-cfe4-41a8-81ed-b68124e7c280";
    private String token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9." +
            "eyJpc3MiOiJ0cmFucy1pdCIsInN1YiI6ImZlNmJhZWU5LTNjM2E" +
            "tNDg2Ny04NThmLWQ3MGM0NmRmOGQxZCIsImV4cCI6MTcwOTUzODk2" +
            "MSwicm9sZXMiOlsiUk9MRV9DVVNUT01FUiJdfQ.rc2Wbrmcvt92w" +
            "uKjiwRULJSr3BgWH_j2KRoZmUcUuiBJGN9x6xNzsj8G_cDwK7pYe2WoNIs46M3af-MYDL1wpQ";

//    @BeforeEach
//    void setUp() throws Exception {
//        logRepository.deleteAll();
//        purchaseRepository.deleteAll();
//        userRepository.deleteAll();
//    }

    @Test
    void createUserSuccess() throws Exception {
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
                    "id": "%s"
                  }
                }
                """.formatted(userCredentialId);

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
                get("/user/"+ userId)
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
                delete("/user/"+ userId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }
}