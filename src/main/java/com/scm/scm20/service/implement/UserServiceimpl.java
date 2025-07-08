package com.scm.scm20.service.implement;

import com.scm.scm20.entities.User;
import com.scm.scm20.repository.UserRepo;
import com.scm.scm20.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    private final Logger logger = LoggerFactory.getLogger(UserServiceimpl.class);

    @Override
    public User saveUser(User user) {
        String UserId = UUID.randomUUID().toString();
        user.setUserId(UserId);
        logger.info("Saving user with email: {}", user.getEmail());
        return userRepo.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
     Optional<User> existingUserOpt = userRepo.findById(user.getUserId());

if (existingUserOpt.isPresent()) {
    User existingUser = existingUserOpt.get(); // ✅ Extract the actual User object

    existingUser.setUsername(user.getUsername());
    existingUser.setEmail(user.getEmail());
    existingUser.setPassword(user.getPassword());
    existingUser.setAbout(user.getAbout());
    existingUser.setProfileLink(user.getProfileLink());
    existingUser.setPhoneNumber(user.getPhoneNumber());

    userRepo.save(existingUser);
    return Optional.of(existingUser);
    } else {
    return Optional.empty();
    }


    }

    @Override
    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }

    @Override
    public boolean isUserExists(String id) {
        return userRepo.existsById(id);
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return userRepo.findByEmail(email).isPresent();
    }
    @Override
        public List<User> getAllUsers() {
            return userRepo.findAll();
        }
}
