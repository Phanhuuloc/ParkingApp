package com.sungwoo.aps.services;

import com.sungwoo.aps.commons.CarStatus;
import com.sungwoo.aps.domain.SungWooModel;
import com.sungwoo.aps.domain.prime.Car;
import com.sungwoo.aps.repo.prime.CarRepo;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author phloc
 */
@Service
public class AndroidPushNotificationsService {
    private final static Logger LOGGER = Logger.getLogger(AndroidPushNotificationsService.class);
    private static final String FIREBASE_SERVER_KEY = "AAAA6sk3dHY:APA91bEe80TK3Jukta8sP1w7dgevuxSrIBO4--dCK7_pqVvg-zUw1mPgr01TFxGtLRCtl-WsNJG_FWfx2XTDHI02nyzNCTfy7EchM2BiibEqPrcs-bENlw66wvcHsOc5PpFOqVlLq4DP";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    private final CarRepo carRepo;

    @Autowired
    public AndroidPushNotificationsService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    /**
     * Send notification
     *
     * @param entity
     * @return
     */
    private CompletableFuture<String> send(HttpEntity<String> entity) {
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);
        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
        return CompletableFuture.completedFuture(firebaseResponse);
    }

    private JSONObject buildData(SungWooModel model) {
        return model.toJSON();
    }

    /**
     * Build the body of FCM
     *
     * @param token device token
     * @return JSONObject present the message body
     * @throws JSONException
     */
    private JSONObject buildBody(String token) throws JSONException {
        JSONObject body = new JSONObject();
        body.put("to", token);
        body.put("priority", "high");
        body.put("message", "message");
        return body;
    }

    /**
     * Build the notification part of FCM
     *
     * @param body notification body
     * @return JSONObject present the message notification
     * @throws JSONException
     */
    private JSONObject buildNotification(String body) throws JSONException {
        JSONObject notification = new JSONObject();
        notification.put("title", "APS Notification");
        notification.put("body", body);
        return notification;
    }

    /**
     * Send finish notification to device
     *
     * @param carUid car uid
     * @return ResponseEntity
     */
    public ResponseEntity<String> pushNotificationToDevice(int carUid){
        Car car = carRepo.findByUid(carUid);
        String token = car.getToken();
        try {
            JSONObject body = buildBody(token);

            String notify = CarStatus.values()[car.getStatus()].toString();
            LOGGER.info(String.format("Car finish moving, send %s to server", notify));

            JSONObject notification = buildNotification(notify);
            body.put("notification", notification);

            JSONObject carObj = buildData(car);
            body.put("data", carObj);

            HttpEntity<String> request = new HttpEntity<>(body.toString());

            CompletableFuture<String> pushNotification = send(request);
            CompletableFuture.allOf(pushNotification).join();

            try {
                String firebaseResponse = pushNotification.get();
                LOGGER.info(firebaseResponse);
                return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error(e.getMessage());
            }
            LOGGER.info("Push Notification ERROR!");
            return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
        }catch (JSONException ignored){
            return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
        }
    }
}
