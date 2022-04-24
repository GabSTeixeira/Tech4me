package com.api.venda.vendams.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.api.venda.vendams.model.Venda;
import com.api.venda.vendams.repository.VendaProdutoRepository;
import com.api.venda.vendams.shared.VendaDto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VendaProdutoServiceImpl implements VendaProdutoService {

    private final ModelMapper  MAPPER = new ModelMapper();
    private final VendaProdutoRepository repository;

    VendaProdutoServiceImpl (VendaProdutoRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<List<VendaDto>> listAll() {
         
        if (repository.count() < 1)  {
            return Optional.empty();
        }
        
        List<VendaDto> ListVendaDto = repository.findAll().stream()
        .map(dat -> MAPPER.map(dat, VendaDto.class)).collect(Collectors.toList());

        return Optional.of(ListVendaDto);
    }


    @Override
    public Optional<VendaDto> listUnique (String id) {

        Optional<Venda> repositoryResponse = repository.findById(id);
        
        if(repositoryResponse.isEmpty()) {
            return Optional.empty();
        }


        // fazer o metodo de buscar unico e dps fazer o metodo de vender
        VendaDto vendaDtoResponse = 

        return null;
    }
    
    
}
