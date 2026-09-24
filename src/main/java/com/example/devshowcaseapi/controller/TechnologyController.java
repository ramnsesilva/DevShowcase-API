package com.example.devshowcaseapi.controller;

import com.example.devshowcaseapi.dto.TechnologyRequestDTO;
import com.example.devshowcaseapi.dto.TechnologyResponseDTO;
import com.example.devshowcaseapi.service.TechnologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
@Tag(name = "technologies", description = "Endpoints para gestao de tecnologias")
public class TechnologyController {

    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping
    @Operation(summary = "Cadastra uma nova tecnologia")
    public ResponseEntity<TechnologyResponseDTO> create(@Valid @RequestBody TechnologyRequestDTO dto) {
        TechnologyResponseDTO created = technologyService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @Operation(summary = "Lista todas as tecnologias cadastradas")
    public ResponseEntity<List<TechnologyResponseDTO>> findAll() {
        List<TechnologyResponseDTO> list = technologyService.findAll();
        return ResponseEntity.ok(list);
    }
}