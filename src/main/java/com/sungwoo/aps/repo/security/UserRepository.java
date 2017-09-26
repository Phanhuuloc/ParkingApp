package com.sungwoo.aps.repo.security;

import com.sungwoo.aps.domain.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}