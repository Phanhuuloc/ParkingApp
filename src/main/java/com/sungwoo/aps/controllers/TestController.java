package com.sungwoo.aps.controllers;

import com.sungwoo.aps.models.Area;
import com.sungwoo.aps.repo.AreaRepo;
import com.sungwoo.aps.support.InsertDB;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Monitor")
@RestController
@Component
@EnableGlobalMethodSecurity(securedEnabled = true)
public class TestController {
    @Value("${name:World}")
    private String name;

    final AreaRepo repo;

    final InsertDB insertDB;

    @Autowired
    public TestController(InsertDB insertDB, AreaRepo repo) {
        this.insertDB = insertDB;
        this.repo = repo;
    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    @GetMapping("/greeting")
    public ResponseEntity getHelloMessage() {
        return new ResponseEntity<>(String.format("Hello %s", this.name), HttpStatus.OK);
    }

    @Secured("ADMIN")
    @GetMapping("/ad/greeting")
    public ResponseEntity getHelloMessageRoleAdmin() {
        return new ResponseEntity<>(String.format("Hello %s", this.name), HttpStatus.OK);
    }

    @GetMapping("/aop/greeting")
    public ResponseEntity getHelloMessageOnSecure(@RequestParam boolean isAdmin) {
        return new ResponseEntity<>(String.format("Hello %s", this.name), HttpStatus.OK);
    }


    @PostMapping("/batch")
    public ResponseEntity batchArea() {
        List<Area> areas = insertDB.initDefaultLocal();
        for (Area area : areas) {
            Area a = repo.findFirstByName(area.getName());
            if (a != null) {
                area.setUid(a.getUid());
            }
            repo.saveAndFlush(area);
        }
        return new ResponseEntity<>(String.format("Hello %s", this.name), HttpStatus.OK);
    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    @GetMapping("/admin/greeting")
    public ResponseEntity getHelloMessageHasSecure() {
        return new ResponseEntity<>(String.format("Hello %s", this.name), HttpStatus.OK);
    }

    @GetMapping("/auth/greeting")
    public ResponseEntity getHelloMessageWithReqAuth() {
        return new ResponseEntity<>(String.format("Hello %s", this.name), HttpStatus.OK);
    }
}
