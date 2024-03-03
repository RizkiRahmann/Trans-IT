package com.pioneers.transit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pioneers.transit.dto.request.LogRequest;
import com.pioneers.transit.entity.Bus;
import com.pioneers.transit.entity.Destination;
import com.pioneers.transit.repository.BusRepository;
import com.pioneers.transit.repository.DestinationRepository;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import org.junit.jupiter.api.BeforeEach;
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
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private String destinationId = "f95e35ee-e22d-4089-b436-8444bf61d7dc";
    private String busId= "0c66bb8b-edad-48ca-b914-4e54aaae0786";

//    @BeforeEach
//    void setUp() throws Exception {
//        logRepository.deleteAll();
//        purchaseRepository.deleteAll();
//        userRepository.deleteAll();
//    }

    @Test
    void saveLog() throws Exception {
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(null);
        Bus bus = busRepository.findById(busId).orElseThrow(null);
        LogRequest request = new LogRequest();
        request.setTicketQuantity(1);
        request.setHotelKey("g303536-d13871137");
        request.setHotelUrl("URL");
        request.setDestination(destination);
        request.setBus(bus);
        String json = objectMapper.writeValueAsString(request);

//        String json = """
//                {
//                  "ticketQuantity": 1,
//                  "hotelKey": "g303536-d13871137",
//                  "hotelUrl": "https://www.tripadvisor.com/Hotel_Review-g303536-d13871137-Reviews-Hotel_Colline_de_France-Gramado_State_of_Rio_Grande_do_Sul.html",
//                  "destination":{
//                    "id":"%s"
//                  },
//                  "bus":{
//                    "id": "%s"
//                  }
//                }
//                """.formatted(destinationId,busId);

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