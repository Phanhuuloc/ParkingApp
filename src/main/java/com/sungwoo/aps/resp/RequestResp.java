package com.sungwoo.aps.resp;

import com.sungwoo.aps.models.Area;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * @author phloc
 */
public class RequestResp {
    List<Point2D.Double> points;
    Area area;
    String value;
    String meaning;

    public RequestResp(String value, String meaning) {
        this.value = value;
        this.meaning = meaning;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public List<Point2D.Double> getPoints() {
        return points;
    }

    public void setPoints(List<Point2D.Double> points) {
        this.points = points;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
