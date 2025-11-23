package br.com.zig.crud.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.zig.crud.repository.PessoaRepository;

@CrossOrigin
@RestController
public class PessoaREST {
    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> obterTodasPessoas() {
        List<Pessoa> lista = pessoaRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> obterPessoaPorId(@PathVariable("id") int id) {
        Optional<Pessoa> op = pessoaRepository.findById(Integer.valueOf((id)));
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> inserirPessoa(@RequestBody Pessoa pessoa) {
        Optional<Pessoa> op = pessoaRepository.findByNome(pessoa.getNome());
        if (op.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(op.get());
        } else {
            pessoa.setId(0);
            pessoaRepository.save(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
        }
    }

    @PutMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> alterar(@PathVariable("id") int id, @RequestBody Pessoa pessoa) {
        Optional<Pessoa> op = pessoaRepository.findById(Integer.valueOf((id)));
        if (op.isPresent()) {
            pessoa.setId(id);
            pessoaRepository.save(pessoa);
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> removerPessoa(@PathVariable("id") int id) {
        Optional<Pessoa> op = pessoaRepository.findById(Integer.valueOf((id)));
        if (op.isPresent()) {
            pessoaRepository.delete(op.get());
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
