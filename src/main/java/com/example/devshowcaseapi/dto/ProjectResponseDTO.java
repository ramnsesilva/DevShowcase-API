package com.example.devshowcaseapi.dto;

import com.example.devshowcaseapi.model.Project;

public record ProjectResponseDTO(
        Long id,
        String title,
        String description,
        String repositoryUrl
) {
    public ProjectResponseDTO(Project project) {
        this(project.getId(), project.getTitle(), project.getDescription(), project.getRepositoryUrl());
    }
}
