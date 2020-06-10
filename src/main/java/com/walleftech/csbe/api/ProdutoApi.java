package com.walleftech.csbe.api;

import com.walleftech.csbe.entities.Produto;
import com.walleftech.csbe.services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoApi {

    private ProdutoService service;

    public ProdutoApi(ProdutoService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping()
    public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        return ResponseEntity.ok(service.atualizar(id, produto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(Long id) {
        service.remover(id);
    }
}
