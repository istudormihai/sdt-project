package com.skillsphere.userservice.factory;

import com.skillsphere.userservice.domain.Role;
import com.skillsphere.userservice.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User createUser(Role role, String email, String username, String rawPassword) {
        return User.builder()
                .email(email)
                .username(username)
                .passwordHash("{noop}" + rawPassword)
                .role(role)
                .active(true)
                .rating(role == Role.SELLER ? 0.0 : null)
                .build();
    }
}
