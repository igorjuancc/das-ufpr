package br.com.zig.crud.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pes")
    @Setter
    @Getter
    private int id;
    @Column(name = "nome_pes")
    @Setter
    @Getter
    private String nome;
    @Column(name = "idade_pes")
    @Setter
    @Getter
    private int idade;
    @Column(name = "data_pes")
    @Setter
    @Getter
    private LocalDate dataDeNascimento;
    @Column(name = "motorista_pes")
    @Setter
    @Getter
    private String motorista;
}
