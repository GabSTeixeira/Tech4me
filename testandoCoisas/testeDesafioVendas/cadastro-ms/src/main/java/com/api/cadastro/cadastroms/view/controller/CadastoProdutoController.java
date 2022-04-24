package com.api.cadastro.cadastroms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.api.cadastro.cadastroms.service.CadastroProdutoService;
import com.api.cadastro.cadastroms.shared.ProdutoDto;
import com.api.cadastro.cadastroms.view.model.ProdutoRequest;
import com.api.cadastro.cadastroms.view.model.ProdutoResponse;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProdutoResponse> getUnique (@PathVariable String id) {
        Optional<ProdutoDto> serviceResponse = service.getUnique(id);


        System.out.println("\n\n\n\n"+serviceResponse.get()+"\n\n\n\n");
        if (serviceResponse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProdutoResponse produtoResponse = MAPPER.map(serviceResponse.get(), ProdutoResponse.class);

        return new ResponseEntity<>(produtoResponse, HttpStatus.FOUND);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ProdutoResponse> postUnique (@RequestBody @Valid ProdutoRequest produtoReq) {
        
        ProdutoDto produtoDtoReq = MAPPER.map(produtoReq, ProdutoDto.class);
        Optional<ProdutoDto> produtoDtoRes = service.postUnique(produtoDtoReq);

        ProdutoResponse produtoResponse = MAPPER.map(produtoDtoRes.get(), ProdutoResponse.class);

        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);

    }
}
