package com.sungwoo.aps.domain.prime;

import com.sungwoo.aps.domain.SungWooModel;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author phloc
 */
@Data
@Entity(name = "parking_area_info")
public class Area implements SungWooModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 16)
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

    public Area(String name, int status,
                double ltLat, double ltLong, double rtLat, double rtLong,
                double rbLat, double rbLong, double lbLat, double lbLong) {
        this.name = name;
        this.status = status;
        this.ltLong = ltLong;
        this.ltLat = ltLat;
        this.rtLong = rtLong;
        this.rtLat = rtLat;
        this.lbLong = lbLong;
        this.lbLat = lbLat;
        this.rbLong = rbLong;
        this.rbLat = rbLat;
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