package com.sungwoo.aps.controllers;

import com.sungwoo.aps.services.AndroidPushNotificationsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author phloc
 */
@Api(value = "Move", description = "the move status API")
@RestController
@RequestMapping("/move")
public class NotificationController implements NotificationApi {

    private final AndroidPushNotificationsService notificationsService;

    @Autowired
    public NotificationController(AndroidPushNotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    @Override
    @ApiOperation(value = "Push finish parking/car-call",
            notes = "After car moved to parking slot / pick up point. C++ swautoparking will submit to WebApp. \n" +
                    "Then WebApp sends a notification to Mobile App via registered access token.\n" +
                    "Ref API: /car/toke",
            response = ResponseEntity.class,
            tags = {"R-S-APM-06",})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @PostMapping(value = "/success")
    public ResponseEntity<String> onCarParkingOrPickupSuccess(@RequestParam("car") int carUid) {
        return notificationsService.pushNotificationToDevice(carUid);
    }
}
