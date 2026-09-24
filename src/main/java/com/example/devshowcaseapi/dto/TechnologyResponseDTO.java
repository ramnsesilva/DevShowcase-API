package com.example.devshowcaseapi.dto;

import com.example.devshowcaseapi.model.Technology;

public record TechnologyResponseDTO(
        Long id,
        String name
) {
    public TechnologyResponseDTO(Technology entity) {
        this(entity.getId(), entity.getName());
    }
}