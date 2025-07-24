package com.smart.smartcontact.Controllers;

import com.smart.smartcontact.Model.Contact;
import com.smart.smartcontact.Services.ContactServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
@Autowired
    private ContactServices contactServices;

@GetMapping("/contacts/{contactid}")
public Contact getContact(@PathVariable String contactid)
{

return contactServices.getById(contactid);
}

}
