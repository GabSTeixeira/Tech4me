package com.api.cadastro.cadastroms.service;

import java.util.List;
import java.util.Optional;

import com.api.cadastro.cadastroms.shared.ProdutoDto;

public interface CadastroProdutoService {
    
    Optional<List<ProdutoDto>> listAll ();
}
