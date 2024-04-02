package com.ms.client.controller;

import com.ms.client.dto.ClientDTO;
import com.ms.client.exceptions.ServiceException;
import com.ms.client.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        try {
            List<ClientDTO> clients = service.findAll();
            if (clients.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clients);
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody @Valid ClientDTO entity) {
        try {
            ClientDTO createdClient = service.create(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value="/getId/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable String id) {
        try {
            ClientDTO client = service.findById(id);
            return ResponseEntity.ok(client);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value="/getEmail/{email}")
    public ResponseEntity<ClientDTO> findByEmail(@PathVariable String email) {
        try {
            ClientDTO client = service.findByEmail(email);
            if (client != null) {
                return ResponseEntity.ok(client);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable String id, @Valid @RequestBody ClientDTO clientDTO) {
        try {
            ClientDTO updatedClient = service.update(id, clientDTO);
            return ResponseEntity.ok(updatedClient);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
