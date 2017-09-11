package com.sungwoo.aps.models;

import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;


/**
 * @author phloc
 */
@Entity
@Table(name = "AUTONOMOUS_CAR_INFO")
public class Car implements SungWooModel {
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

    public Car() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Car{" +
                "uid=" + uid +
                ", token='" + token + '\'' +
                ", status=" + status +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }

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
