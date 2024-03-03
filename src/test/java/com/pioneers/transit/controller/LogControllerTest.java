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
class LogControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String destinationId = "f95e35ee-e22d-4089-b436-8444bf61d7dc";
    private String busId= "0c66bb8b-edad-48ca-b914-4e54aaae0786";

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
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
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
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }
}