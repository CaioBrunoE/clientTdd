package com.ms.tdd.controller;

import com.ms.tdd.model.Client;
import com.ms.tdd.repository.ClientRepository;
import com.ms.tdd.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    ClientRepository repository;

    @GetMapping
    public List<Client> FindAll() {
        return service.findAll();
        /*return Arrays.asList(Client.builder().
                name("Neuber")
                .email("neuber.paiva@gmail.com")
                .cel("9994545429").build());*/
    }

    @PostMapping
    public Client create(@RequestBody Client entity) {
        //entity.setId(ObjectId.get().toString());
        return service.create(entity);
    }

    @GetMapping(value="/{id}")
    public Client FindById(@PathVariable String id){
        return  service.findById(id);
    }

    @PutMapping(value="/{id}")
    public Client updateById(@PathVariable String id , @RequestBody Client client){

     return service.update(id, client);

    }

    @DeleteMapping(value="/{id}")
    public void  delete(@PathVariable String id){
      service.delete(id);
    }
}
