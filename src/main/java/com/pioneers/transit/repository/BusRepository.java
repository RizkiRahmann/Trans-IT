package com.pioneers.transit.repository;

import com.pioneers.transit.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus,String> {
}
