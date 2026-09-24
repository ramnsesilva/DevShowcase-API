package com.example.devshowcaseapi.controller;

import com.example.devshowcaseapi.dto.ProfileRequestDTO;
import com.example.devshowcaseapi.dto.ProfileResponseDTO;
import com.example.devshowcaseapi.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@Tag(name = "profiles", description = "Endpoints para gestão de perfis de desenvolvedores")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo perfil de desenvolvedor")
    public ResponseEntity<ProfileResponseDTO> create(@Valid @RequestBody ProfileRequestDTO dto) {
        ProfileResponseDTO created = profileService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca perfil de desenvolvedor por ID")
    public ResponseEntity<ProfileResponseDTO> findById(@PathVariable Long id) {
        ProfileResponseDTO profile = profileService.findById(id);
        return ResponseEntity.ok(profile);
    }
}