package com.sungwoo.aps.controllers;

import com.sungwoo.aps.domain.prime.Area;
import org.springframework.http.ResponseEntity;


/**
 * @author phloc
 */
public interface AreaApi {
    /**
     *
     * @return
     */
    ResponseEntity requestAllArea();

    /**
     * Handle request car parking, server chose area auto
     *
     * @param carUid     the uid of the car
     * @return ResponseEntity
     */
    ResponseEntity requestCarParking(int carUid);

    /**
     * Handle request create area
     * @param area area
     * @return ResponseEntity
     */
    ResponseEntity createArea(Area area);
}
