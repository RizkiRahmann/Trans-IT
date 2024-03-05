package com.pioneers.transit.repository;

import com.pioneers.transit.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, String>, JpaSpecificationExecutor<Hotel> {
    Optional<Hotel> findByName(String name);
    Optional<Hotel> findByHotelKey(String hotelKey);
}
