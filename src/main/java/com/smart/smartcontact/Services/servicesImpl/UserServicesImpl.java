package com.smart.smartcontact.Services.servicesImpl;
import com.smart.smartcontact.Model.User;
import com.smart.smartcontact.Services.UserServices;
import com.smart.smartcontact.UserRepository.UserRepo;
import com.smart.smartcontact.exceptions.ResourceNotFoundException;
import com.smart.smartcontact.helper.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepo ur;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public User saveUser(User user) {
        //userid has to genreate dynamically
        String userid= UUID.randomUUID().toString();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserId(userid);
        user.setRolelist(List.of(AppConstants.ROLE_USER));

        return ur.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
      User ur3= ur.findByEmail(email).orElse(null);
      return ur3;
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return ur.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return ur.findAll();
    }

    @Override
    public void deleteUser(String userid) {
        User u1= ur.findById(userid).orElseThrow(()->new ResourceNotFoundException("user not found") );
ur.delete(u1);

    }

    @Override
    public boolean isUserExist(String userid) {
        User u1= ur.findById(userid).orElse(null);
        return u1!=null ?true:false;

    }

    @Override
    public boolean isEmailExist(String email) {
        return false;
    }

    @Override
    public Optional<User> updateUser(User user) {
       User u1= ur.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("user not found") );
   u1.setEmail(user.getEmail());
   u1.setName(user.getName());
   u1.setPassword(user.getPassword());
   u1.setAbout(user.getAbout());
   u1.setPhoneNumber(user.getPhoneNumber());
   u1.setProfilePic(user.getProfilePic());
   u1.setEnabled(user.isEnabled());
   u1.setEmailVerified(user.isEmailVerified());
   u1.setPhoneVerified(user.isPhoneVerified());
   u1.setProviders(user.getProviders());
   u1.setProviderId(user.getProviderId());
  User u2= ur.save(u1);
  return Optional.of(u2);
    }
}
