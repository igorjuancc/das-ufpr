package br.com.zig.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zig.crud.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    public Optional<Cidade> findByNome(String nome);
}
