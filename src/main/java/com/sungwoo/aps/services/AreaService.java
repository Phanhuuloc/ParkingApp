package com.sungwoo.aps.services;

import com.sungwoo.aps.models.Area;
import com.sungwoo.aps.repo.AreaRepo;
import com.sungwoo.aps.resp.DummyPath;
import com.sungwoo.aps.resp.RequestResp;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author phloc
 */
@Service
public class AreaService {
    private final static Logger LOGGER = Logger.getLogger(AreaService.class.getName());
    private final AreaRepo areaRepo;
    private final TCPConnection tcpConnection;

    @Autowired
    public AreaService(AreaRepo areaRepo, TCPConnection tcpConnection) {
        this.areaRepo = areaRepo;
        this.tcpConnection = tcpConnection;
    }

    /**
     * Execute parking tcp request
     *
     * @param carId  car uid
     * @param area parking area
     * @throws JSONException
     */
    public RequestResp doOnRequestParking(int carId, Area area) {
        TCPConnection.Permission permission = tcpConnection.execute(area.getUid(), carId);
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
     * @param status status
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
     * @param areaname name
     * @param status status
     * @param ltLat left-top lat
     * @param ltLong left-top long
     * @param rtLat right-top lat
     * @param rtLong right-top long
     * @param lbLat left-bottom lat
     * @param lbLong left bottom long
     * @param rbLat right-bottom lat
     * @param rbLong right-bottom long
     * @return area area
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
        return areaRepo.saveAndFlush(area);
    }
}

