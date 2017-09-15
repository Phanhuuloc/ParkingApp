package com.sungwoo.aps.services;

import com.sungwoo.aps.commons.ApsProperties;
import com.sungwoo.aps.models.Area;
import com.sungwoo.aps.repo.AreaRepo;
import com.sungwoo.aps.resp.DummyPath;
import com.sungwoo.aps.resp.RequestResp;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author phloc
 */
@Service
public class AreaService {
    private final static Logger LOGGER = Logger.getLogger(AreaService.class);
    private final AreaRepo areaRepo;
    private final ApsProperties properties;

    @Autowired
    public AreaService(AreaRepo areaRepo, ApsProperties properties) {
        this.areaRepo = areaRepo;
        this.properties = properties;
    }

    /**
     * Execute parking tcp request
     *
     * @param carId  car uid
     * @param areaId parking uid
     * @throws JSONException
     */
    public RequestResp doOnRequestParking(int carId, Area area) {
        TCPConnection.Permission permission = TCPConnection.init(properties)
                .request(area.getUid(), carId)
                .build()
                .execute();
        RequestResp resp = new RequestResp(String.format("0x%x", permission.getValue()), permission.getDes());
        if (permission.getValue() == TCPConnection.Permission.ALLOW.getValue()) {
            resp.setArea(area);
            resp.setPoints(DummyPath.buildPath());
            return resp;
        }
        return resp;
    }


    /**
     * Find first area that match status
     *
     * @param status
     * @return area
     */
    public Area findFirstByStatus(int status) {
        return areaRepo.findFirstByStatus(status);
    }

    /**
     * Find all area
     *
     * @return list area
     */
    public List<Area> findAll() {
        return areaRepo.findAll();
    }

    /**
     * Create an area
     *
     * @param areaname
     * @param status
     * @param ltLat
     * @param ltLong
     * @param rtLat
     * @param rtLong
     * @param lbLat
     * @param lbLong
     * @param rbLat
     * @param rbLong
     * @return area
     */
    public Area createArea(String areaname, int status, double ltLat, double ltLong, double rtLat,
                           double rtLong, double lbLat, double lbLong, double rbLat, double rbLong) {
        Area area = areaRepo.findFirstByName(areaname);
        if (area == null) {
            area = new Area(areaname);
        }
        area.setStatus(status);
        area.setLtLat(ltLat);
        area.setLtLong(ltLong);
        area.setRtLat(rtLat);
        area.setRtLong(rtLong);
        area.setLbLat(lbLat);
        area.setLbLong(lbLong);
        area.setRbLat(rbLat);
        area.setRbLong(rbLong);
        areaRepo.saveAndFlush(area);
        return area;
    }

    public Area createArea(Area area) {
        Area a = areaRepo.saveAndFlush(area);
        return a;
    }
}

