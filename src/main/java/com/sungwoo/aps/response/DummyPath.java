package com.sungwoo.aps.response;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

public final class DummyPath {

    public static List<Point2D.Double> buildPath(){
        return Arrays.asList(new Point2D.Double(37.4368335, 127.0082145),
                new Point2D.Double(37.4367745, 127.008356),
                new Point2D.Double(37.4368005, 127.00834),
                new Point2D.Double(37.437495275, 127.00881955),
                new Point2D.Double(37.437504417364, 127.00878839939834));
//                new Point2D.Double(10.802102770787128, 106.63972534239294),
//                new Point2D.Double(10.802109357490234, 106.63969315588474),
//                new Point2D.Double(10.802117920204061, 106.63966197520494),
//                new Point2D.Double(10.802127470923045, 106.63966298103333));
    }
}
