package br.com.zig.crud.model;

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
@Table(name="tb_endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_end")
    @Setter
    @Getter
    private int id;
    @Column(name = "rua_end")
    @Setter
    @Getter
    private String rua;
    @Column(name = "numero_end")
    @Setter
    @Getter
    private int numero;
    @Column(name = "complemento_end")
    @Setter
    @Getter
    private String complemento;
    @Column(name = "bairro_end")
    @Setter
    @Getter
    private String bairro;
    @Column(name = "cep_end")
    @Setter
    @Getter
    private String cep;
    @Column(name = "cidade_end")
    @Setter
    @Getter
    private String cidade;
    @Column(name = "estado_end")
    @Setter
    @Getter
    private String estado;
    @Column(name = "residencial_end")
    @Setter
    @Getter
    private Boolean residencial;  
}
