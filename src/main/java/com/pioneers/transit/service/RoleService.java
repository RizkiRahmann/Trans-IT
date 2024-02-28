package com.pioneers.transit.service;

import com.pioneers.transit.entity.Role;
import com.pioneers.transit.utils.constant.ERole;

public interface RoleService {
    Role getOrSave(ERole role);
}
