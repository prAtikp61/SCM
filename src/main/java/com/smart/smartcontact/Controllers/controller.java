package com.smart.smartcontact.Controllers;
import com.smart.smartcontact.Model.User;
import com.smart.smartcontact.Services.UserServices;
import com.smart.smartcontact.forms.UserForm;
import com.smart.smartcontact.helper.MessageType;
import com.smart.smartcontact.helper.Message;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class controller {
    @Autowired
    UserServices us1;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Hello World");
        model.addAttribute("timebro", java.time.LocalDateTime.now());
        return "home";
    }

    @RequestMapping("/home1")
    public String home1(Model model) {
        return "home1";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @RequestMapping("/services")
    public String servvies(Model model) {
        return "services";
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @GetMapping("/signin")
    public String signin(Model model) {
        return "signin";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm1 = new UserForm();
        model.addAttribute("userForm", userForm1);
        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String doRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {


        if(rBindingResult.hasErrors()) {
            return "register";
        }


        System.out.println("Received UserForm: " + userForm.toString());
        User user1 = new User();
        user1.setName(userForm.getName());
        user1.setPassword(userForm.getPassword());
        user1.setEmail(userForm.getEmail());
        user1.setAbout(userForm.getAbout());
        user1.setPhoneNumber(userForm.getPhoneNumber());
        us1.saveUser(user1);

        //message ko generate krna padega in form of object then will pass by model
        Message m1 = new Message();
        m1.setMsg1("user registered successfully");
        m1.setType(MessageType.yellow);
        session.setAttribute("message", m1);

        return "redirect:/register";
    }


}