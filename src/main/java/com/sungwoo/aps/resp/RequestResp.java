package com.sungwoo.aps.resp;

import com.sungwoo.aps.models.Area;
import lombok.Data;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * @author phloc
 */
public @Data
class RequestResp {
    List<Point2D.Double> points;
    Area area;
    String value;
    String meaning;

    public RequestResp(String value, String meaning) {
        this.value = value;
        this.meaning = meaning;
    }

}
