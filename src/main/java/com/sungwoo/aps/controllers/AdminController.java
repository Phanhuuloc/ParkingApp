package com.sungwoo.aps.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api")
public class AdminController implements AdminApi {

    @ApiOperation(value = "Generate a dummy lot on G-MAP on Admin page",
            notes = "Generate a dummy lot on G-MAP on Admin page",
            tags = {" Generate Dummy Lot on G-MAP ",})
    @GetMapping("admin")
    public String requestAdmin() {
        return "admin-page";
    }

    @ApiOperation(hidden = false,
            value = "Api doc",
            notes = "Api doc",
            tags = {"Api Doc",})
    @GetMapping("")
    public String requestHome() {
        return "redirect:./swagger-ui.html";
    }
}
