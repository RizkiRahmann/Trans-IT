package com.pioneers.transit.controller;

import com.pioneers.transit.repository.LogRepository;
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
class PurchaseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String purchaseId = "c4e42287-6199-4f3d-a3d5-5b532494781d";
    private String userId = "b501eac9-8575-4970-a9d0-33c5d702efae";
    private String destinationId = "f95e35ee-e22d-4089-b436-8444bf61d7dc";
    private String busId = "0c66bb8b-edad-48ca-b914-4e54aaae0786";
    @Autowired
    private LogRepository logRepository;
    private String logId = "9ddaa07d-a4a7-4298-892e-b30e069d9c49";

    @Test
    void createPurchase() throws Exception {
        String json = """
                {
                  "purchaseDate": "2020-2-1",
                  "chkIn": "2025-03-05",
                  "chkOut": "2025-03-06",
                  "payment": "DANA",
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
                post(ApiUrlConstant.PURCHASE)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
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
                get(ApiUrlConstant.PURCHASE)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void getPurchaseById() throws Exception {
        mockMvc.perform(
                get(ApiUrlConstant.PURCHASE+"/c4e42287-6199-4f3d-a3d5-5b532494781d")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
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
                  "purchaseDate": "2020-12-1",
                  "payment": "OVO"
                }
                """.formatted(purchaseId);
        mockMvc.perform(
                put(ApiUrlConstant.PURCHASE)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpectAll(
                status().isOk()
        );
    }

    @Test
    void deletePurchase() throws Exception {
        logRepository.deleteById(logId);
        mockMvc.perform(
                delete(ApiUrlConstant.PURCHASE+"/"+purchaseId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + ApiUrlConstant.TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        );
    }
}