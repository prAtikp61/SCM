package com.smart.smartcontact.Controllers;
import com.smart.smartcontact.Model.Contact;
import com.smart.smartcontact.Model.User;
import com.smart.smartcontact.Services.ContactServices;
import com.smart.smartcontact.Services.ImageService;
import com.smart.smartcontact.Services.UserServices;
import com.smart.smartcontact.forms.ContactForm;
import com.smart.smartcontact.forms.ContactSearchForm;
import com.smart.smartcontact.helper.AppConstants;
import com.smart.smartcontact.helper.Message;
import com.smart.smartcontact.helper.MessageType;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.smart.smartcontact.helper.Helperclass;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user/contacts")
public class ContantController {

    @Autowired
    private ContactServices contactService;

    @Autowired
    private UserServices userService;

    @Autowired
    private ImageService imageService;

    @RequestMapping("/add")
    // add contact page: handler
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        return "userFolder/add_contacts";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,
                              Authentication authentication, HttpSession session) {

        if (result.hasErrors()) {
            Message m3 = new Message();
            m3.setMsg1("Contact failed to add");
            m3.setType(MessageType.red);
            session.setAttribute("message", m3);
            return "userFolder/add_contacts";
        }

            String username = Helperclass.returntheemail(authentication);
       // form ---> contact

            User user = userService.getUserByEmail(username);
       // 2 process the contact picture

            // image process

            // uplod karne ka code
            Contact contact = new Contact();
            contact.setName(contactForm.getName());
            contact.setFavorite(contactForm.isFavorite());
            contact.setEmail(contactForm.getEmail());
            contact.setPhoneNumber(contactForm.getPhoneNumber());
            contact.setAddress(contactForm.getAddress());
            contact.setDescription(contactForm.getDescription());
            contact.setUser(user);
            contact.setLinkedInLink(contactForm.getLinkedInLink());
            contact.setWebsiteLink(contactForm.getWebsiteLink());

        if (contactForm.getPicture() != null && !contactForm.getPicture().isEmpty()) {
            String filename = UUID.randomUUID().toString();
            String fileURL = imageService.uploadImage(contactForm.getPicture(), filename);
            contact.setPicture(fileURL);
            contact.setCloudinaryImagePublicId(filename);

        }
            contactService.save(contact);
            System.out.println(contactForm);

            // 3 set the contact picture url

            // 4 `set message to be displayed on the view

        Message m2 = new Message();
        m2.setMsg1("Contact added Successfully");
        m2.setType(MessageType.green);
        session.setAttribute("message", m2);

            return "redirect:/user/contacts/add";

        }


    //contact show
@RequestMapping("/show")
// add contact page: handler
public String showcontact( Model model, Authentication authentication,
                           @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
                           @RequestParam(value = "direction", defaultValue = "asc") String direction) {
    String username = Helperclass.returntheemail(authentication);
    User user = userService.getUserByEmail(username);

    Page<Contact> pageContact=contactService.getByUser(user,page,size,sortBy,direction);
    pageContact.getContent().forEach(c -> System.out.println("🔹✅ Contact: " + c.getName()));


//    System.out.println("✅ this is page contacts "+pageContact);
    model.addAttribute("pageContact", pageContact);
    model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
    return "userFolder/show_contacts";
}



    // search handler

    @RequestMapping("/search")
    public String searchHandler(
            @ModelAttribute ContactSearchForm contactSearchForm,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model,
            Authentication authentication) {
        var user = userService.getUserByEmail(Helperclass.returntheemail(authentication));

        Page<Contact> pageContact=null ;
        System.out.println("📦 Field value: "  + contactSearchForm.getField());
        System.out.println("🔍 Keyword: " + contactSearchForm.getKeyword());
        if (contactSearchForm.getField().equalsIgnoreCase("name")) {
            pageContact = contactService.searchbyname(contactSearchForm.getKeyword(), size, page-1, sortBy, direction,user);
            System.out.println(pageContact.getContent());
        }

        else if (contactSearchForm.getField().equalsIgnoreCase("email")) {
            pageContact = contactService.searchbyemail(contactSearchForm.getKeyword(), size, page-1, sortBy, direction,user);
        }

        else if (contactSearchForm.getField().equalsIgnoreCase("phone")) {
            pageContact = contactService.searchbyphone(contactSearchForm.getKeyword(), size, page-1, sortBy, direction,user);
        }
        System.out.println("Search Keyword: " + contactSearchForm.getKeyword());
        System.out.println("Search Field: "  + contactSearchForm.getField());
        System.out.println("Results: " + pageContact.getContent().size());




        model.addAttribute("contactSearchForm", contactSearchForm);

        model.addAttribute("pageContact", pageContact);

        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);


        return "userFolder/search";
    }

    @RequestMapping("/delete/{id}")
    public String deletecontact(@PathVariable String id){
        contactService.delete(id);
        return "redirect:/user/contacts/show";
    }

    @GetMapping("update/{id}")
    public String updatecontact(@PathVariable String id,Model model){
        var contact = contactService.getById(id);
        model.addAttribute("contact", contact);
        var contactForm = new ContactForm();
        contactForm.setName(contact.getName());
        contactForm.setFavorite(contact.isFavorite());
        contactForm.setEmail(contact.getEmail());
        contactForm.setPhoneNumber(contact.getPhoneNumber());
        contactForm.setAddress(contact.getAddress());
        contactForm.setDescription(contact.getDescription());
        contactForm.setLinkedInLink(contact.getLinkedInLink());
        contactForm.setWebsiteLink(contact.getWebsiteLink());
        contactForm.setPictureLink(contact.getPicture());
        model.addAttribute("contactForm", contactForm);
        model.addAttribute("contactid", id);
        return "userFolder/update";
    }

    @RequestMapping(value="/updateform/{id}",method = RequestMethod.POST)
    public String updatetheform(@PathVariable String id,Model model,ContactForm contactForm)
    {
        var contact = contactService.getById(id);
        contact.setId(id);
        contact.setDescription(contactForm.getDescription());
        contact.setAddress(contactForm.getAddress());
        contact.setFavorite(contactForm.isFavorite());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setPicture(contactForm.getPictureLink());
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());

        if(contactForm.getPicture()!=null && !contactForm.getPicture().isEmpty() ) {
            String fileName = UUID.randomUUID().toString();
            String imageurl = imageService.uploadImage(contactForm.getPicture(), fileName);
            contact.setPicture(imageurl);
            contactForm.setPictureLink(imageurl);
            contact.setCloudinaryImagePublicId(fileName);
        }

       var updatedcon= contactService.update(contact);
       Message message = new Message();
       message.setMsg1("contact updated");
       message.setType(MessageType.green);
       model.addAttribute("message",message);
        return "redirect:/user/contacts/show";

    }
    }
