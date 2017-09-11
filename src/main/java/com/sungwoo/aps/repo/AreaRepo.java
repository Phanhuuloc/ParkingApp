package com.sungwoo.aps.repo;

import com.sungwoo.aps.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author phloc
 */
@Repository
public interface AreaRepo extends JpaRepository<Area, Integer> {

    Area findFirstByStatus(int status);

    Area findFirstByName(String name);
}
