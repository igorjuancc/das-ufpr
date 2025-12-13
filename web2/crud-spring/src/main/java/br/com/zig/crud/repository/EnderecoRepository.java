package br.com.zig.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zig.crud.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
