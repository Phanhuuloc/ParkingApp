package com.sungwoo.aps.repo.prime;


import com.sungwoo.aps.domain.prime.Gate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GateRepo extends JpaRepository<Gate, Integer> {
}
