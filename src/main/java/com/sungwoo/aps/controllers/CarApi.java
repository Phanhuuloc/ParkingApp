package com.sungwoo.aps.controllers;

import org.springframework.http.ResponseEntity;

/**
 * @author phloc
 */
public interface CarApi {

    /**
     * Find a car by uid
     * follow R-A-PIR-03
     *
     * @param uid uid of a car
     * @return car
     */
    ResponseEntity findCarByUid(int uid);

    /**
     * Update token to a car
     *
     * @param uid   uid of this car
     * @param token the token of car
     * @return uid of the car
     */
    ResponseEntity<String> updateCarToken(int uid, String token);

    /**
     * Handle request car call from device
     *
     * @param carUid
     * @return
     */
    ResponseEntity callCar(int carUid);
}
