// package com.scm.scm20;
// import com.scm.scm20.entities.User;
// import com.scm.scm20.repository.UserRepo;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import java.util.List;
// import java.util.UUID;
// import java.util.Optional;
// import java.util.stream.Collectors;
// import java.util.stream.Stream;
// import java.util.ArrayList;
// import java.util.Collection;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import lombok.AccessLevel;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// @Component
// public class TestRunner implements CommandLineRunner {

//     @Autowired
//     private UserRepo userRepo;

//     @Override
//     public void run(String... args) {
//         User user = User.builder()
//                 .userId(UUID.randomUUID().toString())
//                 .name("Imran Manual")
//                 .email("manual@example.com")
//                 .password("password")
//                 .enabled(true)
//                 .roleList(List.of("ROLE_USER", "ROLE_EDITOR"))
//                 .build();

//         userRepo.save(user);

//         User fetched = userRepo.findByEmail("manual@example.com").orElse(null);
//         if (fetched != null) {
//             System.out.println("✅ Roles from DB:");
//             fetched.getRoleList().forEach(System.out::println);
//         } else {
//             System.out.println("❌ User not found");
//         }
//     }
// }
