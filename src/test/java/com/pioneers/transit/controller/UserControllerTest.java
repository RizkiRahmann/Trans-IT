package com.pioneers.transit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pioneers.transit.dto.request.UserRequest;
import com.pioneers.transit.entity.Image;
import com.pioneers.transit.entity.User;
import com.pioneers.transit.entity.UserCredential;
import com.pioneers.transit.repository.*;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;


import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

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
    private ImageRepository imageRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ObjectMapper objectMapper;
    private String userCredentialId = "8499c0d0-fc21-44ed-b5f2-a80b7823373f";

    @BeforeEach
    void setUp() throws Exception {
        logRepository.deleteAll();
        purchaseRepository.deleteAll();
        userRepository.deleteAll();
        imageRepository.deleteAll();
    }

    @Test
    void createUserSuccess() throws Exception {
//        UserCredential userCredential = userCredentialRepository.findById("23cc327b-ddc6-4b31-9394-24a1e7872938")
//                .orElseThrow(null);
//        UserRequest request = new UserRequest();
//        request.setUsername("chiteki");
//        request.setName("Rizki");
//        request.setBirthDate(Date.valueOf("2200-1-1"));
//        request.setAddress("Jalan");
//        request.setPhoneNumber("808010102020");
//        request.setUserCredential(userCredential);
//
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
                post(ApiUrlConstant.USER)
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
                get(ApiUrlConstant.USER)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void getById() throws Exception {
        UserCredential uC = new UserCredential();
        uC.setId(UUID.randomUUID().toString());
        uC.setEmail("rizki11@gmail.com");
        uC.setPassword("12345");
        UserCredential userCredential = userCredentialRepository.save(uC);

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("chiteki1");
        user.setName("rizki2");
        user.setBirthDate(Date.valueOf("2222-09-01"));
        user.setAddress("jalan");
        user.setPhoneNumber("0808090901101");
        user.setUserCredentiall(userCredential);
        userRepository.save(user);

        mockMvc.perform(
                get(ApiUrlConstant.USER+"/"+ user.getId())
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );

        userRepository.deleteById(user.getId());
        userCredentialRepository.deleteById(uC.getId());
    }

    @Test
    void updateUser() throws Exception {
//        String json = """
//                {
//                   "id": "%s",
//                   "username": "chiteki2",
//                   "name": "Rizki2",
//                   "birthDate" : "2222-11-5",
//                   "address": "jalan aja",
//                   "phoneNumber": "080809090101"
//                 }
//                """.formatted(userId);

        UserCredential uC = new UserCredential();
        uC.setId(UUID.randomUUID().toString());
        uC.setEmail("rizki51@gmail.com");
        uC.setPassword("12345");
        UserCredential userCredential = userCredentialRepository.save(uC);

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("chiteki1");
        user.setName("rizki2");
        user.setBirthDate(Date.valueOf("2222-09-01"));
        user.setAddress("jalan");
        user.setPhoneNumber("0808090901101");
        user.setUserCredentiall(userCredential);
        userRepository.save(user);

        UserRequest request = new UserRequest();
        request.setId(user.getId());
        request.setUsername("Rahman12345");
        request.setName("Rizki");
        request.setBirthDate(Date.valueOf("2200-1-1"));
        request.setAddress("Jalan");
        request.setPhoneNumber("808010102020");

        mockMvc.perform(
                put(ApiUrlConstant.USER)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isCreated()
        );

        userRepository.deleteById(user.getId());
        userCredentialRepository.deleteById(uC.getId());
    }

    @Test
    void updateUserImageBadReq() throws Exception {
//        Random rand = new Random();
//        Long randomNumber = rand.nextLong(100);
//        Image image = new Image();
//        image.setId(randomNumber);
//        image.setName("image");
//        image.setType("png");
//        image.setImageData(new byte[]{10});
//        imageRepository.save(image);

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        userRepository.save(user);

        mockMvc.perform(
                put(ApiUrlConstant.USER)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
        ).andExpectAll(
                status().isBadRequest()
        );

        userRepository.deleteAll();
        imageRepository.deleteAll();
    }

    @Test
    void deleteById() throws Exception {
        UserCredential uC = new UserCredential();
        uC.setId(UUID.randomUUID().toString());
        uC.setEmail("rizki51@gmail.com");
        uC.setPassword("12345");
        UserCredential userCredential = userCredentialRepository.save(uC);

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("chiteki1");
        user.setName("rizki2");
        user.setBirthDate(Date.valueOf("2222-09-01"));
        user.setAddress("jalan");
        user.setPhoneNumber("0808090901101");
        user.setUserCredentiall(userCredential);
        userRepository.save(user);

        mockMvc.perform(
                delete(ApiUrlConstant.USER+"/"+ user.getId())
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );

        userRepository.deleteById(user.getId());
        userCredentialRepository.deleteById(uC.getId());
    }
}