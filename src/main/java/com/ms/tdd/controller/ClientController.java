package com.ms.tdd.controller;

import com.ms.tdd.controller.exception.NullPointerExceptionHandle;
import com.ms.tdd.dto.ClientDTO;
import com.ms.tdd.services.ClientService;
import com.ms.tdd.controller.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<ClientDTO> list = service.findAll();
        if (list.isEmpty()) {
            throw new ObjectNotFoundException("List esta vazia");
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO entity) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
        } catch (Exception e) {
            throw new NullPointerExceptionHandle("Prencha todos os campos");
        }
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            throw new ObjectNotFoundException("ID incorreto :" + id);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable String id, @RequestBody ClientDTO clientDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, clientDTO));
        } catch (Exception e) {
            throw new ObjectNotFoundException("ID incorreto :" + id);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException("ID incorreto :" + id);
        }
    }
}
