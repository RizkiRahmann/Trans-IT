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

    private String destinationId = "4b601ce1-919b-4e85-aed3-5609c3dd1886";
    private String token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9." +
            "eyJpc3MiOiJ0cmFucy1pdCIsInN1YiI6ImZlNmJhZWU5LTNjM2E" +
            "tNDg2Ny04NThmLWQ3MGM0NmRmOGQxZCIsImV4cCI6MTcwOTUzODk2" +
            "MSwicm9sZXMiOlsiUk9MRV9DVVNUT01FUiJdfQ.rc2Wbrmcvt92w" +
            "uKjiwRULJSr3BgWH_j2KRoZmUcUuiBJGN9x6xNzsj8G_cDwK7pYe2WoNIs46M3af-MYDL1wpQ";
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
                get("/destination")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(
                get("/destination/"+ destinationId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
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
                delete("/destination/"+ destinationId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }
}