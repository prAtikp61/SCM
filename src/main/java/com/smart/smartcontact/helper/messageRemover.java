package com.smart.smartcontact.helper;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class messageRemover {
    public static void remove(){
       try {
           System.out.println("removing message from session");
           HttpSession session =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
           session.removeAttribute("message");
       }catch (Exception e) {
           System.out.println("error");
           e.printStackTrace();
       }

       }
    }
