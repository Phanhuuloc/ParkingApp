package com.sungwoo.aps.repo.security;

import com.sungwoo.aps.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);

}
