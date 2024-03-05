package com.pioneers.transit.service;

import com.pioneers.transit.dto.request.HotelRequest;
import com.pioneers.transit.dto.response.HotelResponse;
import com.pioneers.transit.dto.response.HotelResponseClient;
import com.pioneers.transit.entity.Hotel;

import java.sql.Date;

public interface HotelServiceClient {
    HotelResponseClient get(String chkIn, String chkOut, String hotelKey);
    HotelResponseClient getByHotelKey(String hotelKey);
    HotelResponse create(HotelRequest request);
    Hotel getOrSave(String hotelKey);
    HotelResponse update(HotelRequest request);
//    void delete(String id);
}
