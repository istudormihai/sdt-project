package com.skillsphere.userservice.dto;

import com.skillsphere.userservice.domain.Role;

public record UserResponse(
        Long id, String email, String username, Role role, String bio, Double rating
) {}