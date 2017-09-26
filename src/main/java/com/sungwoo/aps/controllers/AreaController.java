package com.sungwoo.aps.controllers;

import com.sungwoo.aps.commons.AreaStatus;
import com.sungwoo.aps.domain.prime.Area;
import com.sungwoo.aps.response.ParkingAreaResponse;
import com.sungwoo.aps.response.TcpResultResponse;
import com.sungwoo.aps.services.AreaService;
import com.sungwoo.aps.services.GateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author phloc
 */
@Api(value = "Area", description = "Area Api")
@RestController
@RequestMapping("area")
public class AreaController implements AreaApi {
    private final AreaService areaService;
    private final GateService gateService;

    @Autowired
    public AreaController(AreaService areaService, GateService gateService) {
        this.areaService = areaService;
        this.gateService = gateService;
    }

    @Override
    @ApiOperation(value = "Get all parking area info",
            notes = "Mobile App submits to get parking area info: name, status, long, lat....",
            response = ResponseEntity.class,
            tags = {"R-A-PIR-01, R-A-PIR-02"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)})
    @GetMapping(value = "all", produces = {"application/json", "text/json"})
    public ResponseEntity requestAllArea() {
        List<Area> area = areaService.findAll();
        if (area != null) {
            return new ResponseEntity<>(new ParkingAreaResponse<>(area, gateService.findAll()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("count not find any area!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(value = "Request parking",
            notes = "Mobile App submits a \"PARKING\" request. \n" +
                    "After WebApp recieved this request, it forwards to C++ swautoparking service to process with car.",
            response = ResponseEntity.class,
            tags = {"R-S-PR-01, R-S-PR-02"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)})
    @PostMapping(value = "parking", produces = {"application/json", "text/json"})
    public ResponseEntity requestCarParking(@RequestParam("car") int carUid) {
        Area area = areaService.findFirstByStatus(AreaStatus.EMPTY.value());
        if (area != null) {
            TcpResultResponse resp = areaService.doOnRequestParking(carUid, area);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ApiOperation(hidden = true,
            value = "Create parking area",
            notes = "Create parking area",
            response = ResponseEntity.class,
            tags = {"Create"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)})
    @PostMapping(value = "create", consumes = MediaType.ALL_VALUE,
            produces = {MediaType.ALL_VALUE})
    public ResponseEntity createArea(Area area) {
        Area a = areaService.createArea(area);
        if (a != null) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
