package com.pioneers.transit.service.impl;

import com.pioneers.transit.entity.Role;
import com.pioneers.transit.repository.RoleRepository;
import com.pioneers.transit.service.RoleService;
import com.pioneers.transit.utils.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(ERole role) {
        // kalau role ada di table kita ambil
        Optional<Role> byRole = roleRepository.findByRole(role);
        if (byRole.isPresent()) return byRole.get();

        // jika tidak ada, kita simpan
        Role roleInstance = Role.builder()
                .role(role)
                .build();
        return roleRepository.save(roleInstance);
    }
}
