package com.sungwoo.aps.response;

import com.sungwoo.aps.domain.prime.Area;
import lombok.Data;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * @author phloc
 */
@Data
public class TcpResultResponse {
    List<Point2D.Double> points;
    Area area;
    String value;
    String meaning;

    public TcpResultResponse(String value, String meaning) {
        this.value = value;
        this.meaning = meaning;
    }

}
