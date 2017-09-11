package com.sungwoo.aps.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController implements AdminApi {

    @ApiOperation(value = "Page submit new area", notes = "Page submit new area",
            tags = {"Create",})
    @GetMapping("/admin")
    public String requestAdmin() {
        return "admin-page";
    }

    @ApiOperation(value = "Page submit new area", notes = "Page submit new area",
            tags = {"Api Doc",})
    @GetMapping("/")
    public String requestHome() {
        return "redirect:swagger-ui.html";
    }
}
