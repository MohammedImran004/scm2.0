package com.scm.scm20.service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.scm20.entities.User;
import com.scm.scm20.repository.UserRepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
      if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        System.out.println("✅ Email: " + user.getEmail());
        System.out.println("✅ Password: " + user.getPassword());
        System.out.println("✅ Username: " + user.getName()); // Change to getUsername() if applicable
        return user;
        } else {
        throw new UsernameNotFoundException("User not found with email: " + username);
     }
    }
}