package com.api.cadastro.cadastroms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.api.cadastro.cadastroms.model.Produto;
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


    @Override
    public Optional<ProdutoDto> getUnique (String id) {
        
        Optional<Produto> repositoryResponse = repository.findById(id);
        
        if (repositoryResponse.isEmpty()) {
            return Optional.empty();
        }
        
        Optional<ProdutoDto> produtoDtoResponse = Optional.of(MAPPER.map(repositoryResponse.get(), ProdutoDto.class));
        return produtoDtoResponse;
    }


    @Override
    public Optional<ProdutoDto> postUnique(ProdutoDto produto) {

        Produto repositoryRequest = MAPPER.map(produto, Produto.class);
        Produto repositoryResponse = repository.save(repositoryRequest);
        ProdutoDto produtoResponse = MAPPER.map(repositoryResponse, ProdutoDto.class);
        
        return Optional.of(produtoResponse);
    }
    
}
