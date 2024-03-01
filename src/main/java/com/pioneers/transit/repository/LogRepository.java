package com.pioneers.transit.repository;

import com.pioneers.transit.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log,String> {
}
