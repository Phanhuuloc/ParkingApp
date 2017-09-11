package com.sungwoo.aps.controllers;

import com.sungwoo.aps.models.Car;
import com.sungwoo.aps.resp.PermissionResp;
import com.sungwoo.aps.services.CarService;
import com.sungwoo.aps.services.TCPConnection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author phloc
 */
@Api(value = "Car", description = "the car API")
@RestController
@RequestMapping("/car")
public class CarController implements CarApi {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Override
    @ApiOperation(value = "Get car info by id", notes = "Get car info by uid", response = Car.class,
            tags = {"R-S-APM-03"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Car.class)})
    @GetMapping(value = "/info/{uid}", produces = {"application/json", "text/json"})
    public Car findCarByUid(@PathVariable("uid") int uid) {
        return carService.findByUid(uid);
    }

    @Override
    @ApiOperation(value = "Update car token", notes = "Update car token", response = UUID.class, tags = {"Notification Token",})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = UUID.class)})
    @PostMapping(value = "/token")
    public ResponseEntity<String> updateCarToken(@RequestParam("uid") int uid, @RequestParam("token") String token) {
        if (StringUtils.isNotBlank(token)) {
            carService.updateCarToken(uid, token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Token is null", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "Car call request", notes = "Car call", response = ResponseEntity.class,
            tags = {"R-S-CS-01, R-S-CS-02, R-S-CS-03"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)})
    @PostMapping(value = "/call", produces = {"application/json", "text/json"})
    public ResponseEntity callCar(@RequestParam("car") int carUid) {
        TCPConnection.Permission resp = carService.carCall(carUid);
        PermissionResp permissionResp = new PermissionResp(String.format("0x%x", resp.getValue()), resp.getDes());
        return new ResponseEntity<>(permissionResp, HttpStatus.OK);
    }
}
