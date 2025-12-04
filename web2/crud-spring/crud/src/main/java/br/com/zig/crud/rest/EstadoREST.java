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

import br.com.zig.crud.model.Estado;
import br.com.zig.crud.repository.EstadoRepository;

@CrossOrigin
@RestController
public class EstadoREST {
    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/estados")
    public ResponseEntity<List<Estado>> obterTodosEnderecos() {
        List<Estado> lista = estadoRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/estados/{id}")
    public ResponseEntity<Estado> obterPessoaPorId(@PathVariable("id") int id) {
        Optional<Estado> op = estadoRepository.findById(Integer.valueOf((id)));
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/estados")
    public ResponseEntity<Estado> inserirEndereco(@RequestBody Estado estado) {
        Optional<Estado> op = estadoRepository.findByNome(estado.getNome());
        if (op.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(op.get());
        }

        op = estadoRepository.findBySigla(estado.getSigla());
        if (op.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(op.get());
        } else {
            estado.setId(0);
            estadoRepository.save(estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(estado);
        }
    }

    @PutMapping("/estados/{id}")
    public ResponseEntity<Estado> alterar(@PathVariable("id") int id, @RequestBody Estado estado) {
        Optional<Estado> op = estadoRepository.findById(Integer.valueOf((id)));
        if (op.isPresent()) {
            estado.setId(id);
            estadoRepository.save(estado);
            return ResponseEntity.ok(estado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/estados/{id}")
    public ResponseEntity<Estado> removerPessoa(@PathVariable("id") int id) {
        Optional<Estado> op = estadoRepository.findById(Integer.valueOf((id)));
        if (op.isPresent()) {
            estadoRepository.delete(op.get());
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
