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

import br.com.zig.crud.model.Endereco;
import br.com.zig.crud.repository.EnderecoRepository;

@CrossOrigin
@RestController
public class EnderecoREST {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/enderecos")
    public ResponseEntity<List<Endereco>> obterTodosEnderecos() {
        List<Endereco> lista = enderecoRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/enderecos/{id}")
    public ResponseEntity<Endereco> obterEnderecoPorId(@PathVariable("id") int id) {
        Optional<Endereco> op = enderecoRepository.findById(Integer.valueOf((id)));
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/enderecos")
    public ResponseEntity<Endereco> inserirEndereco(@RequestBody Endereco endereco) {
        endereco.setId(0);
        enderecoRepository.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

    @PutMapping("/enderecos/{id}")
    public ResponseEntity<Endereco> alterar(@PathVariable("id") int id, @RequestBody Endereco endereco) {
        Optional<Endereco> op = enderecoRepository.findById(Integer.valueOf((id)));
        if (op.isPresent()) {
            endereco.setId(id);
            enderecoRepository.save(endereco);
            return ResponseEntity.ok(endereco);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/enderecos/{id}")
    public ResponseEntity<Endereco> removerEndereco(@PathVariable("id") int id) {
        Optional<Endereco> op = enderecoRepository.findById(Integer.valueOf((id)));
        if (op.isPresent()) {
            enderecoRepository.delete(op.get());
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
