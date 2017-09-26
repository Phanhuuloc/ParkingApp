package com.sungwoo.aps.repo.prime;

import com.sungwoo.aps.domain.prime.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author phloc
 */
@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

    Car findByUid(Integer uid);

}
