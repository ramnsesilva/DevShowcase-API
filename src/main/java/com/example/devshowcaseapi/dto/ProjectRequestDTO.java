package com.example.devshowcaseapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
        String title,

        @Size(max = 1000, message = "A descrição não pode exceder 1000 caracteres")
        String description,

        @NotBlank(message = "O link do repositório é obrigatório")
        String repositoryUrl
) {}