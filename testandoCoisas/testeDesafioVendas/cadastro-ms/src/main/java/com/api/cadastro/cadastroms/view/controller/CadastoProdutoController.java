package com.api.cadastro.cadastroms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.api.cadastro.cadastroms.service.CadastroProdutoService;
import com.api.cadastro.cadastroms.shared.ProdutoDto;
import com.api.cadastro.cadastroms.view.model.ProdutoResponse;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cadastro")
public class CadastoProdutoController {
    
    private final ModelMapper MAPPER = new ModelMapper();
    private final CadastroProdutoService service;

    CadastoProdutoController (CadastroProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> getAll () {
        Optional<List<ProdutoDto>> serviceResponse = service.listAll();
        
        if (serviceResponse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<ProdutoResponse> produtoResponseList = serviceResponse.get().stream()
        .map(dat -> MAPPER.map(dat, ProdutoResponse.class)).collect(Collectors.toList());

        return new ResponseEntity<>(produtoResponseList, HttpStatus.OK);
    
    }
}
