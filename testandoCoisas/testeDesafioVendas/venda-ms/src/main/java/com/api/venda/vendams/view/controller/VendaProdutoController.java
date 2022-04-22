package com.api.venda.vendams.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.api.venda.vendams.service.VendaProdutoService;
import com.api.venda.vendams.shared.VendaDto;
import com.api.venda.vendams.view.model.VendaResponse;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/venda")
public class VendaProdutoController {
    
    private final ModelMapper MAPPER = new ModelMapper();
    private final VendaProdutoService service;

    VendaProdutoController (VendaProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<VendaResponse>> getAll () {
        Optional<List<VendaDto>> serviceResponse = service.listAll();
        
        if (serviceResponse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<VendaResponse> produtoResponseList = serviceResponse.get().stream()
        .map(dat -> MAPPER.map(dat, VendaResponse.class)).collect(Collectors.toList());

        return new ResponseEntity<>(produtoResponseList, HttpStatus.OK);
    
    }

}
