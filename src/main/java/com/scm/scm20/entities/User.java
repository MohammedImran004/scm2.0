package com.scm.scm20.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String userId;
    @Column(name = "user_name", nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String password;
    private String email;
    @Column(columnDefinition = "TEXT")
    private String about;
    @Column(columnDefinition = "TEXT")
    private String profileLink;
    private String phoneNumber;
    private boolean enabled;
    private boolean emailVerified;
    private boolean phoneVerified;
    private Providers provider = Providers.SELF; // Default provider
    private String providerId;
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contact> contacts = new ArrayList<>();
}
