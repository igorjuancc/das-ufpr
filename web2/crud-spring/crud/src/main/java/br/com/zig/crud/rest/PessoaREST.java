package br.com.zig.crud.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zig.crud.model.Pessoa;

@CrossOrigin
@RestController
public class PessoaREST {
    public static List<Pessoa> pessoas = new ArrayList<>();

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> obterTodasPessoas() {
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> obterPessoaPorId(
            @PathVariable("id") int id) {
        Pessoa p = pessoas.stream().filter(pess -> pess.getId() == id)
                .findAny().orElse(null);
        if (p == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.ok(p);
    }

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> inserirPessoa(@RequestBody Pessoa pessoa) {
        Pessoa p = pessoas.stream().filter(
                pess -> pess.getNome().equals(pessoa.getNome()))
                .findAny().orElse(null);
        if (p != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        p = pessoas.stream().max(Comparator.comparing(Pessoa::getId))
                .orElse(null);
        if (p == null)
            pessoa.setId(1);
        else
            pessoa.setId(p.getId() + 1);
        pessoas.add(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
    }

    @PutMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> alterar(@PathVariable("id") int id,
            @RequestBody Pessoa pessoa) {
        Pessoa p = pessoas.stream().filter(pess -> pess.getId() == id)
                .findAny().orElse(null);
        if (p != null) {
            p.setNome(pessoa.getNome());
            p.setIdade(pessoa.getIdade());
            p.setDataNascimento(pessoa.getDataNascimento());
            p.setMotorista(pessoa.getMotorista());
            return ResponseEntity.ok(p);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> removerPessoa(@PathVariable("id") int id) {
        Pessoa pessoa = pessoas.stream().filter(pess -> pess.getId() == id)
                .findAny().orElse(null);
        if (pessoa != null) {
            pessoas.removeIf(p -> p.getId() == id);
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
