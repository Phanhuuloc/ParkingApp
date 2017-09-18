package com.sungwoo.aps.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class AopController {
    @Value("${name:World}")
    private String name;

    @GetMapping("/greeting")
    public ResponseEntity getHelloMessage() {
        return new ResponseEntity<>(String.format("Hello %s", this.name), HttpStatus.OK);
    }
}
