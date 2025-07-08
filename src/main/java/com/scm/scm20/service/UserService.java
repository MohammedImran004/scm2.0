package com.scm.scm20.service;
import java.util.*;
import com.scm.scm20.entities.User;
import org.springframework.stereotype.Service;
import com.scm.scm20.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public interface UserService {
    User saveUser(User user);
    User getUserByEmail(String email);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExists(String id);
    boolean isUserExistsByEmail(String email);
    List<User> getAllUsers();
}
