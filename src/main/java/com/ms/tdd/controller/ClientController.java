package com.ms.tdd.controller;

import com.ms.tdd.model.Client;
import com.ms.tdd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @GetMapping
    public List<Client> FindAll() {
        return repository.findAll();
        /*return Arrays.asList(Client.builder().
                name("Neuber")
                .email("neuber.paiva@gmail.com")
                .cel("9994545429").build());*/
    }

    @PostMapping
    public Client create(@RequestBody Client entity) {
        //entity.setId(ObjectId.get().toString());
        return repository.save(entity);
    }

    @GetMapping(value="/{id}")
    public Client FindById(@PathVariable String id){
        return  repository.findById(id).get();
    }

    @PutMapping(value="/{id}")
    public Client updateById(@PathVariable String id , @RequestBody Client client){

     Client entidade =  repository.findById(id).get();
        entidade.setName(client.getName());
        entidade.setEmail(client.getEmail());
        entidade.setCel(client.getCel());
        entidade.setCpf(client.getCpf());

        return  repository.save(entidade);

    }

    @DeleteMapping(value="/{id}")
    public void  deleteeById(@PathVariable String id){
        Client entidade =  repository.findById(id).get();
        repository.delete(entidade);

    }


}
