package br.com.tech4me.primeirorest.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.primeirorest.compartilhado.PessoaDto;

public interface PessoaService {
    PessoaDto criarPessoa(PessoaDto pessoa);
    List<PessoaDto> obterTodos();
    Optional<PessoaDto> obterPorId(String id);
    void removerPessoa(String id);
    PessoaDto atualizarPessoa(String id, PessoaDto pessoa);
}
