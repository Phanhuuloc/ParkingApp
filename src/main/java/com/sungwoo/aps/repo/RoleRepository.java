package com.sungwoo.aps.repo;

import com.sungwoo.aps.models.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);

}
