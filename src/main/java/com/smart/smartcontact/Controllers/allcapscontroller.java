package com.smart.smartcontact.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class allcapscontroller {

//by HttpServletRequest
//    @RequestMapping("/tocaps")
//    public String allcaps(HttpServletRequest request, Model model){
//        String name = request.getParameter("broo");
//       String caps=name.toUpperCase();
//       model.addAttribute("caps",caps);
//        return "finalcaps";
//    }

    //by annotation of @RequestParam(name)
    @GetMapping("/tocaps")
    public String allcaps(@RequestParam("broo") String namee , Model model){
        String name =namee;
        String caps="hey my bro"+name.toUpperCase();
        model.addAttribute("caps",caps);
        return "finalcaps";
    }

}
