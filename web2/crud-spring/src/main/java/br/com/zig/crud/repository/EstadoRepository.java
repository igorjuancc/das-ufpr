package br.com.zig.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zig.crud.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    public Optional<Estado> findByNome(String nome);
    public Optional<Estado> findBySigla(String sigla);
}
