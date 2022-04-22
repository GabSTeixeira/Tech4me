package com.api.cadastro.cadastroms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.api.cadastro.cadastroms.repository.CadastroProdutoRepository;
import com.api.cadastro.cadastroms.shared.ProdutoDto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CadastroProdutoServiceImpl implements CadastroProdutoService {

    
    private final ModelMapper MAPPER = new ModelMapper();
    private final CadastroProdutoRepository repository;

    CadastroProdutoServiceImpl (CadastroProdutoRepository repository){
        this.repository = repository;
    }    
    
    
    @Override
    public Optional<List<ProdutoDto>> listAll() {
        
        if (repository.count() < 1)  {
            return Optional.empty();
        }

        List<ProdutoDto> ListProdutoDto = repository.findAll().stream()
        .map(dat -> MAPPER.map(dat, ProdutoDto.class)).collect(Collectors.toList());

        return Optional.of(ListProdutoDto);
    }
    
}
