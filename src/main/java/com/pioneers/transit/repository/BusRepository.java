package com.pioneers.transit.repository;

import com.pioneers.transit.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BusRepository extends JpaRepository<Bus,String>, JpaSpecificationExecutor<Bus> {
}
