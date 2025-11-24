package com.skillsphere.userservice.service;

import com.skillsphere.userservice.domain.User;
import com.skillsphere.userservice.dto.CreateUserRequest;
import com.skillsphere.userservice.dto.UserResponse;
import com.skillsphere.userservice.factory.UserFactory;
import com.skillsphere.userservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserResponse register(CreateUserRequest req) {
        if (userRepository.findByEmail(req.email()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = userFactory.createUser(req.role(), req.email(), req.username(), req.password());
        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    public UserResponse getUser(Long id) {
        return userRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    private UserResponse toResponse(User u) {
        return new UserResponse(u.getId(), u.getEmail(), u.getUsername(),
                u.getRole(), u.getBio(), u.getRating());
    }
}