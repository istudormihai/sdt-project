package com.skillsphere.userservice.dto;

import com.skillsphere.userservice.domain.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(
        @NotBlank String email,
        @NotBlank String username,
        @NotBlank String password,
        @NotNull Role role
) {}
