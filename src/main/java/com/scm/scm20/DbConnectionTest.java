package com.scm.scm20;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.scm.scm20.repository.UserRepository;

import org.springframework.stereotype.Component;

@Component
public class DbConnectionTest implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Checking DB connection...");
        long count = userRepository.count();  // this forces DB connection
        System.out.println("✅ User count in DB: " + count);
    }
}
