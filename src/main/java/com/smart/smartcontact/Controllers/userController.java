package com.smart.smartcontact.Controllers;

import com.smart.smartcontact.Model.User;
import com.smart.smartcontact.Services.UserServices;
import com.smart.smartcontact.Services.servicesImpl.UserServicesImpl;
import com.smart.smartcontact.helper.Helperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class userController {


    //user dashboard page
    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public String dashboard() {
        return "userFolder/dashboard";
    }

    //user profile page
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Authentication authentication, Model model) {
        return "userFolder/profile";
    }


}
