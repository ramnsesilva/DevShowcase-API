package com.example.devshowcaseapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record ProfileRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        String bio,

        @URL(message = "URL do GitHub inválida")
        String githubUrl,

        @URL(message = "URL do LinkedIn inválida")
        String linkedinUrl
) {}