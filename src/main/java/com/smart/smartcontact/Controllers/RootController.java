package com.smart.smartcontact.Controllers;

import com.smart.smartcontact.Model.User;
import com.smart.smartcontact.Services.UserServices;
import com.smart.smartcontact.helper.Helperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {
    @Autowired
    private UserServices userServices;

    @ModelAttribute
    public void addloggedinuserinfo(Model model, Authentication authentication){
        if(authentication == null){
            return;
        }
        System.out.println("adding loggedinuserinfo");
        String username = Helperclass.returntheemail(authentication);
        User user = userServices.getUserByEmail(username);
        System.out.println("✅ Logged in user: " + user.getName() + " | " + user.getEmail());
        model.addAttribute("loggedinuser", user);
        model.addAttribute("image",user.getProfilePic());
    }

}
