package com.sungwoo.aps.models;

import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author phloc
 */
@Entity(name = "PARKING_AREA_INFO")
public @Data
class Area implements SungWooModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", length = 16)
    private int uid;
    @Column(length = 1024)
    private String name;
    @Min(0)
    @Max(1)
    private int status;
    @Column(name = "sensor_alive_status")
    private double sensorStatus;
    @Column(name = "lefttop_y")
    private double ltLong;
    @Column(name = "lefttop_x")
    private double ltLat;
    @Column(name = "righttop_y")
    private double rtLong;
    @Column(name = "righttop_x")
    private double rtLat;
    @Column(name = "leftbottom_y")
    private double lbLong;
    @Column(name = "leftbottom_x")
    private double lbLat;
    @Column(name = "rightbottom_y")
    private double rbLong;
    @Column(name = "rightbottom_x")
    private double rbLat;

    public Area() {
    }

    public Area(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public Area(String name) {
        this.name = name;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject body = new JSONObject();
        try {
            body.put("uid", uid);
            body.put("area", name);
            body.put("status", status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return body;
    }

}
