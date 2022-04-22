package com.api.venda.vendams.repository;

import com.api.venda.vendams.model.Venda;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaProdutoRepository extends MongoRepository<Venda,String> {  
}
