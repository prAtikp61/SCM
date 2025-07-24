package com.smart.smartcontact.Services;

import com.smart.smartcontact.Model.Contact;
import com.smart.smartcontact.Model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContactServices {

   // Save contact
   Contact save(Contact contact);

   // Update contact
   Contact update(Contact contact);

   // Get all contacts
   List<Contact> getAll();

   // Get contact by ID
   Contact getById(String id);

   // Delete contact by ID
   void delete(String id);

   // Get contacts by User ID
   List<Contact> getByUserId(String userId);

   // Get contacts by User with pagination and sorting
   Page<Contact> getByUser(User user, int page, int size, String sort, String direction);

   // Search contacts by name with pagination and sorting
   Page<Contact> searchbyname(String name, int page, int size, String sort, String direction,User user);

   // Search contacts by email with pagination and sorting
   Page<Contact> searchbyemail(String email, int page, int size, String sort, String direction,User  user);

   // Search contacts by phone number with pagination and sorting
   Page<Contact> searchbyphone(String phoneNumber, int page, int size, String sort, String direction,User user);
}
