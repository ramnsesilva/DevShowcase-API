package com.example.devshowcaseapi.service;

import com.example.devshowcaseapi.dto.ProfileRequestDTO;
import com.example.devshowcaseapi.dto.ProfileResponseDTO;
import com.example.devshowcaseapi.exception.ResourceNotFoundException;
import com.example.devshowcaseapi.model.Profile;
import com.example.devshowcaseapi.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional
    public ProfileResponseDTO create(ProfileRequestDTO dto) {
        if (profileRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Já existe um perfil cadastrado com este e-mail.");
        }

        Profile profile = new Profile();
        profile.setName(dto.name().trim());
        profile.setEmail(dto.email().trim().toLowerCase());
        profile.setBio(dto.bio());
        profile.setGithubUrl(dto.githubUrl());
        profile.setLinkedinUrl(dto.linkedinUrl());

        profile = profileRepository.save(profile);
        return new ProfileResponseDTO(profile);
    }

    @Transactional(readOnly = true)
    public ProfileResponseDTO findById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado com o id: " + id));
        return new ProfileResponseDTO(profile);
    }
}