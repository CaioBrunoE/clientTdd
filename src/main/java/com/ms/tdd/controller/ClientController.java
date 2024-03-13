package com.ms.tdd.controller;

import com.ms.tdd.dto.ClientDTO;
import com.ms.tdd.services.ClientService;
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
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO entity) {
        return ResponseEntity.ok().body(service.create(entity));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body( service.findById(id));
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable String id , @RequestBody ClientDTO clientDTO){
     return ResponseEntity.status(HttpStatus.OK).body(service.update(id, clientDTO));
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable String id){
      service.delete(id);
    }
}
