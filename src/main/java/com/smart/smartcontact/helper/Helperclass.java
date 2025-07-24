package com.smart.smartcontact.helper;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.security.Principal;

public class Helperclass {

    public static String returntheemail(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken token) {
            OAuth2User oauth2User = (OAuth2User) token.getPrincipal();
            String provider = token.getAuthorizedClientRegistrationId();

            if (provider.equalsIgnoreCase("google")) {
                return oauth2User.getAttribute("email");
            } else if (provider.equalsIgnoreCase("github")) {
                String email = oauth2User.getAttribute("email");
                return email != null ? email : oauth2User.getAttribute("login") + "@gmail.com";
            }
            // Add more providers if needed
        }

        // Fallback for local login
        return authentication.getName();
    }

}