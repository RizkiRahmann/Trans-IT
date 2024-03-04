package com.pioneers.transit.service;

import com.pioneers.transit.dto.request.HotelRequest;
import com.pioneers.transit.dto.response.HotelResponse;
import com.pioneers.transit.dto.response.HotelResponseClient;

import java.sql.Date;

public interface HotelServiceClient {
    HotelResponseClient get(String chkIn, String chkOut, String hotelKey);
    HotelResponse create(HotelRequest request);
    HotelResponse update(HotelRequest request);
    void delete(String id);
}
