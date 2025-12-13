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

import br.com.zig.crud.model.Login;
import br.com.zig.crud.model.Usuario;
import br.com.zig.crud.repository.UsuarioRepository;

@CrossOrigin
@RestController
public class UsuarioREST {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        List<Usuario> lista = usuarioRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable("id") int id) {
        Optional<Usuario> op = usuarioRepository.findById(Integer.valueOf(id));

        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
        Optional<Usuario> op = usuarioRepository.findByLogin(usuario.getLogin());

        if (op.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(op.get());
        } else {
            usuario.setId(0);
            usuarioRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        Optional<Usuario> op = usuarioRepository.findById(Integer.valueOf(id));
        if (op.isPresent()) {
            usuario.setId(id);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> remover(@PathVariable("id") int id) {
        Optional<Usuario> op = usuarioRepository.findById(Integer.valueOf(id));
        if (op.isPresent()) {
            usuarioRepository.delete(op.get());
            return ResponseEntity.ok(op.get());
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Login login) {
        Optional<Usuario> op = usuarioRepository.findByLoginAndSenha(login.getLogin(), login.getSenha());
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();        
    }
}
