package com.smart.smartcontact.Controllers;

import com.smart.smartcontact.Model.Contact;
import com.smart.smartcontact.Model.Feedback;
import com.smart.smartcontact.Model.User;
import com.smart.smartcontact.UserRepository.FeedbackRepo;
import com.smart.smartcontact.forms.ContactForm;
import com.smart.smartcontact.forms.FeedbackForm;
import com.smart.smartcontact.helper.Helperclass;
import com.smart.smartcontact.helper.Message;
import com.smart.smartcontact.helper.MessageType;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class FeedbackController {

    @Autowired
    FeedbackRepo feedbackRepo;

    @RequestMapping("/contactus")
    // add contact page: handler
    public String showfeedback(Model model) {
     FeedbackForm feedbackForm = new FeedbackForm();

        model.addAttribute("feedbackform",feedbackForm );
        return "/contact";
    }

    @RequestMapping(value = "/addfeedback", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute FeedbackForm feedbackForm,BindingResult result
                          ) {
        Feedback feedback = new Feedback();
        feedback.setName(feedbackForm.getName());
        feedback.setEmail(feedbackForm.getEmail());
        feedback.setMessage(feedbackForm.getMessage());
        feedbackRepo.save(feedback);
        System.out.println(feedbackForm);
        return "redirect:/contactus";

    }

}
