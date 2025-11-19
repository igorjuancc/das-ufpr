package br.com.zig.crud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private String nome;
    @Setter
    @Getter
    private int idade;
    @Setter
    @Getter
    private String dataDeNascimento;
    @Setter
    @Getter
    private String motorista;
}
