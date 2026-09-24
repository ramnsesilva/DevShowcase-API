package com.example.devshowcaseapi.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import java.util.Set;

public record ProjectRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        String title,

        @NotBlank(message = "A descrição é obrigatória")
        String description,

        @URL(message = "URL inválida")
        String repositoryUrl,

        Set<Long> technologyIds
) {}