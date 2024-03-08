package com.ms.tdd.services;

import com.ms.tdd.model.Client;
import com.ms.tdd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public List<Client> findAll() {
        return repository.findAll();
    }


    public Client create(Client entity) {
        return repository.save(entity);

    }

    public Client findById(String id) {
        return repository.findById(id).get();
    }

    public Client update(String id , Client client){

        Client entity =  repository.findById(id).get();

        entity.setName(client.getName());
        entity.setEmail(client.getEmail());
        entity.setCel(client.getCel());
        entity.setCpf(client.getCpf());

        return  repository.save(entity);
    }

    public void delete(String id) {
        Client entity  = repository.findById(id).get();
       repository.delete(entity);
    }
}
