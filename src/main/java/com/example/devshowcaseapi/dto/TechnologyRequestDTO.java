package com.example.devshowcaseapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TechnologyRequestDTO(
        @NotBlank(message = "O nome da tecnologia e obrigatorio")
        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
        String name
) {}