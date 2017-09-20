package com.sungwoo.aps.repo;

import com.sungwoo.aps.models.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}