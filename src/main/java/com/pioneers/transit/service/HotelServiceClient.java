package com.pioneers.transit.service;

import com.pioneers.transit.dto.response.HotelResponseClient;

import java.sql.Date;

public interface HotelServiceClient {
    HotelResponseClient get(String chkIn, String chkOut, String hotelKey);
}
