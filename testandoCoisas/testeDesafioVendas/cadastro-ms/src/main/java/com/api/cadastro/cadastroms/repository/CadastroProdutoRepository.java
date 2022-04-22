package com.api.cadastro.cadastroms.repository;

import com.api.cadastro.cadastroms.model.Produto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroProdutoRepository extends MongoRepository<Produto, String> {
}
