package com.sungwoo.aps.repo;

import com.sungwoo.aps.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author phloc
 */
@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

    Car findByUid(Integer uid);

}
