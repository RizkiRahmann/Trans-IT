package com.pioneers.transit.controller;

import com.pioneers.transit.utils.constant.ApiUrlConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvc.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DestinationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private String destinationId = "991be390-84a0-4f77-8586-157aac2946ab";
    @Test
    void createDestination() throws Exception {
        String json = """
                {
                   "name": "Santai",
                   "description": "Wisata Pantai",
                   "location" : "Bali",
                   "price": 100000,
                   "rating": 3.5
                 }
                """;

        mockMvc.perform(
                post("/destination")
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
                get("/destination")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(
                get("/destination/225a31de-d3bd-4c80-957f-5f743ba281ca")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void updateDestination() throws Exception {
        String json = """
               {
                   "id": "%s",
                   "name": "Santai",
                   "description": "Wisata Pantai",
                   "location" : "Hawai",
                   "price": 100000,
                   "rating": 3
               }
                """.formatted(destinationId);

        mockMvc.perform(
                put("/destination")
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
                delete("/destination/"+ destinationId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }
}