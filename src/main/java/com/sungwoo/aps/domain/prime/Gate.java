package com.sungwoo.aps.domain.prime;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "store_starting_info")
public class Gate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 16)
    private int uid;
    @Column(length = 1024)
    private String name;
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

    public Gate() {
    }

    public Gate(String name,
                double ltLat, double ltLong, double rtLat, double rtLong,
                double rbLat, double rbLong, double lbLat, double lbLong) {
        this.name = name;
        this.ltLong = ltLong;
        this.ltLat = ltLat;
        this.rtLong = rtLong;
        this.rtLat = rtLat;
        this.lbLong = lbLong;
        this.lbLat = lbLat;
        this.rbLong = rbLong;
        this.rbLat = rbLat;
    }
}
