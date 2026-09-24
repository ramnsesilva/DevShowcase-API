package com.example.devshowcaseapi.service;

import com.example.devshowcaseapi.dto.ProjectRequestDTO;
import com.example.devshowcaseapi.dto.ProjectResponseDTO;
import com.example.devshowcaseapi.model.Project;
import com.example.devshowcaseapi.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<ProjectResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(ProjectResponseDTO::new)
                .toList();
    }

    public ProjectResponseDTO findById(Long id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registo não encontrado com o ID: " + id));
        return new ProjectResponseDTO(project);
    }

    public ProjectResponseDTO create(ProjectRequestDTO dto) {
        Project project = new Project();
        project.setTitle(dto.title());
        project.setDescription(dto.description());
        project.setRepositoryUrl(dto.repositoryUrl());

        project = repository.save(project);
        return new ProjectResponseDTO(project);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Registo não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }
}