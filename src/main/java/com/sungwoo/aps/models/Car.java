package com.sungwoo.aps.models;

import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;


/**
 * @author phloc
 */
@Entity(name = "AUTONOMOUS_CAR_INFO")
public @Data
class Car implements SungWooModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CAR_ID", length = 16)
    private int uid;
    @Column(name = "date_time", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    private String token;
    @Min(0)
    @Max(5)
    private int status;
    @Column(name = "lon_location")
    private double lon;
    @Column(name = "lat_location")
    private double lat;

    @Override
    public JSONObject toJSON() {
        JSONObject body = new JSONObject();
        try {
            body.put("uid", uid);
            body.put("long", lon);
            body.put("lat", lat);
            body.put("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return body;
    }
}
