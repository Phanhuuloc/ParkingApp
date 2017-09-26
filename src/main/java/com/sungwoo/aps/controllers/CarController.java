package com.sungwoo.aps.controllers;

import com.sungwoo.aps.domain.prime.Car;
import com.sungwoo.aps.response.TcpResultResponse;
import com.sungwoo.aps.services.CarService;
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
@RequestMapping("car")
public class CarController implements CarApi {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Override
    @ApiOperation(value = "Get car info",
            notes = "Mobile App will submit to get car info by uid every 5 seconds.",
            response = Car.class,
            tags = {"R-S-APM-03"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Car.class)})
    @GetMapping(value = "info/{uid}", produces = {"application/json", "text/json"})
    public ResponseEntity<Car> findCarByUid(@PathVariable("uid") int uid) {
        Car car = carService.findByUid(uid);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    @ApiOperation(value = "Register access token",
            notes = "When launch Mobile App, it submits to WebApp to register an access token.\n" +
                    "This token is used for sending notification to Mobile App later.",
            response = UUID.class,
            tags = {" Register Mobile Access Token",})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = UUID.class)})
    @PostMapping(value = "token")
    public ResponseEntity<String> updateCarToken(@RequestParam("uid") int uid, @RequestParam("token") String token) {
        if (StringUtils.isNotBlank(token)) {
            carService.updateCarToken(uid, token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Token is null", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "Car call request",
            notes = "Mobile App submits a \"CAR CALL\" request. \n" +
                    "After WebApp recieved this request \n, " +
                    "it forwards to C++ swautoparking service to process with car",
            response = ResponseEntity.class,
            tags = {"R-S-CS-01, R-S-CS-02, R-S-CS-03"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)})
    @PostMapping(value = "call", produces = {"application/json", "text/json"})
    public ResponseEntity callCar(@RequestParam("car") int carUid) {
        TcpResultResponse resp = carService.carCall(carUid);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
