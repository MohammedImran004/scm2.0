package com.scm.scm20.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User implements UserDetails {

    @Id
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Getter(AccessLevel.NONE)
    private String password;

    @Column(length = 1000)
    private String about;

    @Column(length = 1000)
    private String profilePic;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Getter(AccessLevel.NONE)
    private boolean enabled = false;

    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Providers provider = Providers.SELF;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roleList = new ArrayList<>();

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
