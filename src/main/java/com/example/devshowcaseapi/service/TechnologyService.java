package com.example.devshowcaseapi.service;

import com.example.devshowcaseapi.dto.TechnologyRequestDTO;
import com.example.devshowcaseapi.dto.TechnologyResponseDTO;
import com.example.devshowcaseapi.model.Technology;
import com.example.devshowcaseapi.repository.TechnologyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TechnologyService {

    private final TechnologyRepository technologyRepository;

    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Transactional
    public TechnologyResponseDTO create(TechnologyRequestDTO dto) {
        Technology technology = new Technology();
        technology.setName(dto.name().trim());
        technology = technologyRepository.save(technology);
        return new TechnologyResponseDTO(technology);
    }

    @Transactional(readOnly = true)
    public List<TechnologyResponseDTO> findAll() {
        return technologyRepository.findAll()
                .stream()
                .map(TechnologyResponseDTO::new)
                .toList();
    }
}