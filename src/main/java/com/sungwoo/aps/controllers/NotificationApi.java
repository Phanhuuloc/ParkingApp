package com.sungwoo.aps.controllers;

import org.springframework.http.ResponseEntity;

/**
 * @author phloc
 */
public interface NotificationApi {
    /**
     *  Handle request update finish car call/parking
     *
     * @param carUid
     * @return
     */
    ResponseEntity<String> onCarParkingOrPickupSuccess(int carUid);
}
