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

@SpringBootTest
@AutoConfigureMockMvc

class HotelClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9." +
            "eyJpc3MiOiJ0cmFucy1pdCIsInN1YiI6ImZlNmJhZWU5LTNjM2E" +
            "tNDg2Ny04NThmLWQ3MGM0NmRmOGQxZCIsImV4cCI6MTcwOTUzODk2" +
            "MSwicm9sZXMiOlsiUk9MRV9DVVNUT01FUiJdfQ.rc2Wbrmcvt92w" +
            "uKjiwRULJSr3BgWH_j2KRoZmUcUuiBJGN9x6xNzsj8G_cDwK7pYe2WoNIs46M3af-MYDL1wpQ";
    @Test
    void geHotel() throws Exception {
        mockMvc.perform(
                get("/hotel")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .param("chk_in", "2024-01-01")
                        .param("chk_out", "2024-02-01")
                        .param("hotel_key", "g17441565-d15046244")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }
}