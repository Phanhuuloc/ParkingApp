package com.sungwoo.aps.models;

import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author phloc
 */
@Entity(name = "PARKING_AREA_INFO")
public class Area implements SungWooModel {

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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(double sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public double getLtLong() {
        return ltLong;
    }

    public void setLtLong(double ltLong) {
        this.ltLong = ltLong;
    }

    public double getLtLat() {
        return ltLat;
    }

    public void setLtLat(double ltLat) {
        this.ltLat = ltLat;
    }

    public double getRtLong() {
        return rtLong;
    }

    public void setRtLong(double rtLong) {
        this.rtLong = rtLong;
    }

    public double getRtLat() {
        return rtLat;
    }

    public void setRtLat(double rtLat) {
        this.rtLat = rtLat;
    }

    public double getLbLong() {
        return lbLong;
    }

    public void setLbLong(double lbLong) {
        this.lbLong = lbLong;
    }

    public double getLbLat() {
        return lbLat;
    }

    public void setLbLat(double lbLat) {
        this.lbLat = lbLat;
    }

    public double getRbLong() {
        return rbLong;
    }

    public void setRbLong(double rbLong) {
        this.rbLong = rbLong;
    }

    public double getRbLat() {
        return rbLat;
    }

    public void setRbLat(double rbLat) {
        this.rbLat = rbLat;
    }

    @Override
    public String toString() {
        return "Area{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", ltLong=" + ltLong +
                ", ltLat=" + ltLat +
                ", rtLong=" + rtLong +
                ", rtLat=" + rtLat +
                ", lbLong=" + lbLong +
                ", lbLat=" + lbLat +
                ", rbLong=" + rbLong +
                ", rbLat=" + rbLat +
//                ", lot=" + lot +
                '}';
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
