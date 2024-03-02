package com.pioneers.transit.controller;

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

import static org.springframework.test.web.servlet.MockMvc.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private String busId = "e75d7067-858d-4d1b-8d22-9cb2339c030d";
    private String token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9." +
            "eyJpc3MiOiJ0cmFucy1pdCIsInN1YiI6ImZlNmJhZWU5LTNjM2E" +
            "tNDg2Ny04NThmLWQ3MGM0NmRmOGQxZCIsImV4cCI6MTcwOTUzODk2" +
            "MSwicm9sZXMiOlsiUk9MRV9DVVNUT01FUiJdfQ.rc2Wbrmcvt92w" +
            "uKjiwRULJSr3BgWH_j2KRoZmUcUuiBJGN9x6xNzsj8G_cDwK7pYe2WoNIs46M3af-MYDL1wpQ";

    @Test
    void createBus() throws Exception {
        String json = """
                {
                  "name": "Bus Jingga",
                  "chair" : "30",
                  "price": "100000"
                }
                """;

        mockMvc.perform(
                post("/bus")
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
                get("/bus")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(
                get("/user/"+ busId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void updateBus() throws Exception {
        String json = """
                {
                "id":"%s",
                  "name": "Bus Jingga",
                  "chair" : "30",
                  "price": "500000"
                }
                """.formatted(busId);

        mockMvc.perform(
                put("/bus")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpectAll(
                status().isCreated()
        );
    }

    @Test
    void deleteBus() throws Exception {
        mockMvc.perform(
                delete("/bus/"+ busId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }
}