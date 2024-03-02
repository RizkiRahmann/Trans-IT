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
class LogControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String logId = "9fed65fd-89ff-4efd-af9a-9aac78360a58";
    private String purchaseId = "756cffb2-cfe4-41a8-81ed-b68124e7c280";
    private String destinationId = "f95e35ee-e22d-4089-b436-8444bf61d7dc";
    private String busId= "65cad4da-9671-478c-bdae-840757e84960";
    private String token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9." +
            "eyJpc3MiOiJ0cmFucy1pdCIsInN1YiI6ImZlNmJhZWU5LTNjM2E" +
            "tNDg2Ny04NThmLWQ3MGM0NmRmOGQxZCIsImV4cCI6MTcwOTUzODk2" +
            "MSwicm9sZXMiOlsiUk9MRV9DVVNUT01FUiJdfQ.rc2Wbrmcvt92w" +
            "uKjiwRULJSr3BgWH_j2KRoZmUcUuiBJGN9x6xNzsj8G_cDwK7pYe2WoNIs46M3af-MYDL1wpQ";

    @Test
    void saveLog() throws Exception {
        String json = """
                {
                  "ticketQuantity": 1,
                  "hotelKey": "g303536-d13871137",
                  "hotelUrl": "https://www.tripadvisor.com/Hotel_Review-g303536-d13871137-Reviews-Hotel_Colline_de_France-Gramado_State_of_Rio_Grande_do_Sul.html",
                  "destination":{
                    "id":"%s"
                  },
                  "bus":{
                    "id": "%s"
                  }
                }
                """.formatted(destinationId,busId);

        mockMvc.perform(
                post("/log")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void getAllLog() throws Exception {
        mockMvc.perform(
                get("/log")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }
}