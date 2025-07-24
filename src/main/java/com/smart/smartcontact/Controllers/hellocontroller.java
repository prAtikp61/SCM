package com.smart.smartcontact.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class hellocontroller {

    @RequestMapping("/showform")
    public String hellocontroller() {
        return "showform";
    }

    @RequestMapping("/doform")
    public String processform() {
        return "doform";
    }
}