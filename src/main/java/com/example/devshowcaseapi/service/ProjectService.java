package com.example.devshowcaseapi.service;

import com.example.devshowcaseapi.dto.ProjectRequestDTO;
import com.example.devshowcaseapi.dto.ProjectResponseDTO;
import com.example.devshowcaseapi.exception.ResourceNotFoundException;
import com.example.devshowcaseapi.model.Project;
import com.example.devshowcaseapi.model.Technology;
import com.example.devshowcaseapi.repository.ProjectRepository;
import com.example.devshowcaseapi.repository.TechnologyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectService(ProjectRepository projectRepository, TechnologyRepository technologyRepository) {
        this.projectRepository = projectRepository;
        this.technologyRepository = technologyRepository;
    }

    @Transactional
    public ProjectResponseDTO create(ProjectRequestDTO dto) {
        Project project = new Project();
        project.setTitle(dto.title());
        project.setDescription(dto.description());
        project.setRepositoryUrl(dto.repositoryUrl());

        if (dto.technologyIds() != null && !dto.technologyIds().isEmpty()) {
            List<Technology> foundTechnologies = technologyRepository.findAllById(dto.technologyIds());
            project.setTechnologies(new HashSet<>(foundTechnologies));
        }

        project = projectRepository.save(project);
        return new ProjectResponseDTO(project);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProjectResponseDTO findById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com o id: " + id));
        return new ProjectResponseDTO(project);
    }

    @Transactional
    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Projeto não encontrado com o id: " + id);
        }
        projectRepository.deleteById(id);
    }
}