package com.pioneers.transit.repository;

import com.pioneers.transit.entity.Role;
import com.pioneers.transit.utils.constant.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByRole(ERole role);
}
