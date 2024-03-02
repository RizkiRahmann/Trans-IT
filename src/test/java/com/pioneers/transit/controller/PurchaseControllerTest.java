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
class PurchaseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String purchaseId = "75c6df8d-594b-44e8-820e-6f8185c0404f";
    private String userId = "9fed65fd-89ff-4efd-af9a-9aac78360a58";
    private String destinationId = "f95e35ee-e22d-4089-b436-8444bf61d7dc";
    private String busId = "0c66bb8b-edad-48ca-b914-4e54aaae0786";
    private String token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9." +
            "eyJpc3MiOiJ0cmFucy1pdCIsInN1YiI6ImZlNmJhZWU5LTNjM2E" +
            "tNDg2Ny04NThmLWQ3MGM0NmRmOGQxZCIsImV4cCI6MTcwOTUzODk2" +
            "MSwicm9sZXMiOlsiUk9MRV9DVVNUT01FUiJdfQ.rc2Wbrmcvt92w" +
            "uKjiwRULJSr3BgWH_j2KRoZmUcUuiBJGN9x6xNzsj8G_cDwK7pYe2WoNIs46M3af-MYDL1wpQ";

    @Test
    void createPurchase() throws Exception {
        String json = """
                {
                  "purchaseDate": "2024-2-1",
                  "user": {
                    "id":"%s"
                  },
                  "logs": [
                    {
                      "ticketQuantity": 1,
                      "hotelKey": "g303536-d13871137",
                      "hotelUrl": "https://www.tripadvisor.com/Hotel_Review-g303536-d13871137-Reviews-Hotel_Colline_de_France-Gramado_State_of_Rio_Grande_do_Sul.html",
                      "destination":
                      {
                      "id":"%s"
                      },
                      "bus":
                      {
                        "id": "%s"
                      }
                    }
                  ]
                }
                """.formatted(userId,destinationId,busId);
        mockMvc.perform(
                post("/purchase")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(
                get("/purchase")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void getPurchaseById() throws Exception {
        mockMvc.perform(
                get("/purchase/"+ purchaseId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void updatePurchase() throws Exception {
        String json = """
                {
                  "id": "%s",
                  "purchaseDate": "2020-12-1"
                }
                """.formatted(purchaseId);
        mockMvc.perform(
                put("/purchase")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void deletePurchase() throws Exception {
        mockMvc.perform(
                delete("/purchase/"+ purchaseId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }
}