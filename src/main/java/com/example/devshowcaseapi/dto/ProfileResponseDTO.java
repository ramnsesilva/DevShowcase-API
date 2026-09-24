package com.example.devshowcaseapi.dto;

import com.example.devshowcaseapi.model.Profile;

public record ProfileResponseDTO(
        Long id,
        String name,
        String email,
        String bio,
        String githubUrl,
        String linkedinUrl
) {
    public ProfileResponseDTO(Profile entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getBio(),
                entity.getGithubUrl(),
                entity.getLinkedinUrl()
        );
    }
}