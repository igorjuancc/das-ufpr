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

import br.com.zig.crud.model.Cidade;
import br.com.zig.crud.repository.CidadeRepository;

@CrossOrigin
@RestController
public class CidadeREST {
    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping("/cidades")
    public ResponseEntity<List<Cidade>> obterTodasCidades() {
        List<Cidade> lista = cidadeRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/cidades/{id}")
    public ResponseEntity<Cidade> obterCidadePorId(@PathVariable("id") int id) {
        Optional<Cidade> op = cidadeRepository.findById(Integer.valueOf((id)));
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/cidades")
    public ResponseEntity<Cidade> inserirCidade(@RequestBody Cidade cidade) {
        Optional<Cidade> op = cidadeRepository.findByNome(cidade.getNome());
        if (op.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(op.get());
        } else {
            cidade.setId(0);
            cidadeRepository.save(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        }
    }

    @PutMapping("/cidades/{id}")
    public ResponseEntity<Cidade> alterar(@PathVariable("id") int id, @RequestBody Cidade cidade) {
        Optional<Cidade> op = cidadeRepository.findById(Integer.valueOf((id)));
        if (op.isPresent()) {
            cidade.setId(id);
            cidadeRepository.save(cidade);
            return ResponseEntity.ok(cidade);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/cidades/{id}")
    public ResponseEntity<Cidade> removerCidade(@PathVariable("id") int id) {
        Optional<Cidade> op = cidadeRepository.findById(Integer.valueOf((id)));
        if (op.isPresent()) {
            cidadeRepository.delete(op.get());
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
