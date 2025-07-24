package com.smart.smartcontact.Services.servicesImpl;

import com.smart.smartcontact.UserRepository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private UserRepo repo1;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return      repo1.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("not found re baba"));

    }
}
