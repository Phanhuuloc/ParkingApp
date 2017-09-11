package com.sungwoo.aps.services;

import com.sungwoo.aps.commons.ApsProperties;
import com.sungwoo.aps.models.Car;
import com.sungwoo.aps.repo.CarRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author phloc
 */
@Service
public class CarService {
    private final static Logger LOGGER = Logger.getLogger(CarService.class);
    private final CarRepo carRepo;
    private final ApsProperties properties;

    @Autowired
    public CarService(CarRepo carRepo, ApsProperties properties) {
        this.carRepo = carRepo;
        this.properties = properties;
    }

    /**
     * Return Car contain uid
     *
     * @param uid car uid
     * @return
     */
    public Car findByUid(Integer uid) {
        return carRepo.findByUid(uid);
    }

    /**
     * Execute car call tcp request
     *
     * @param carId car id
     */
    public TCPConnection.Permission carCall(int carId) {
        return TCPConnection.init(properties)
                .request(carId)
                .build()
                .execute();
    }

    /**
     * Update car token
     *
     * @param uid car id
     * @param token token
     */
    public void updateCarToken(int uid, String token) {
        Car car = carRepo.findByUid(uid);
        car.setToken(token);
        carRepo.save(car);
    }
}
