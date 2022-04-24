package com.api.venda.vendams.service;

import java.util.List;
import java.util.Optional;

import com.api.venda.vendams.shared.VendaDto;

public interface VendaProdutoService {
    
    Optional<List<VendaDto>> listAll ();
    Optional<VendaDto> listUnique (String id);
}
