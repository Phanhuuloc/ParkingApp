package com.sungwoo.aps.services;

import com.sungwoo.aps.models.Car;
import com.sungwoo.aps.repo.CarRepo;
import com.sungwoo.aps.resp.DummyPath;
import com.sungwoo.aps.resp.RequestResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;

/**
 * @author phloc
 */
@Service
public class CarService {
    //    private final static Logger LOGGER = Logger.getLogger(CarService.class.getName());
    private final CarRepo carRepo;
    private final TCPConnection tcpConnection;

    @Autowired
    public CarService(CarRepo carRepo, TCPConnection tcpConnection) {
        this.carRepo = carRepo;
        this.tcpConnection = tcpConnection;
    }

    /**
     * Return Car contain uid
     *
     * @param uid car uid
     * @return car
     */
    public Car findByUid(Integer uid) {
        return carRepo.findByUid(uid);
    }

    /**
     * Execute car call tcp request
     *
     * @param carId car id
     */
    public RequestResp carCall(int carId) {
        TCPConnection.Permission permission = tcpConnection.execute(carId);
        RequestResp resp = new RequestResp(String.format("0x%x", permission.getValue()), permission.getDes());
        if (permission.getValue() == TCPConnection.Permission.ALLOW.getValue()) {
            List<Point2D.Double> path = DummyPath.buildPath();
            Collections.reverse(path);
            resp.setPoints(path);
            return resp;
        }
        return resp;
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
