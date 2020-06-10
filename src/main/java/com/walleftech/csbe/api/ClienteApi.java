package com.walleftech.csbe.api;

import com.walleftech.csbe.entities.Cliente;
import com.walleftech.csbe.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteApi {

    private ClienteService service;

    public ClienteApi(ClienteService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping()
    public ResponseEntity<Cliente> save(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id,@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.atualizar(id, cliente));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id) {
        service.remover(id);
    }
}
