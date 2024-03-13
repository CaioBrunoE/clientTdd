package com.ms.tdd.services;

import com.ms.tdd.dto.ClientDTO;
import com.ms.tdd.model.Client;
import com.ms.tdd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public List<ClientDTO> findAll() {
        List<Client> list = repository.findAll();
        List<ClientDTO> listDTO = list.stream().map(ClientDTO::new).toList();
        return listDTO ;
    }

    public ClientDTO create(ClientDTO clientDTO) {
        Client entity = new Client(clientDTO);
        repository.save(entity);
        return new ClientDTO(entity);
    }

    public ClientDTO findById(String id) {
        Client entity =  repository.findById(id).get();
        return new ClientDTO(entity);
    }

    public ClientDTO update(String id , ClientDTO clientDTO){
        Client entity = repository.findById(id).get();
        entity.setName(clientDTO.getName());
        entity.setCel(clientDTO.getCel());
        entity.setEmail(clientDTO.getEmail());
        entity.setCpf(clientDTO.getCpf());
        repository.save(entity);
        return new ClientDTO(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
