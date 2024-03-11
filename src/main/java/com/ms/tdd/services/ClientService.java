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
        List<Client> listEntidade = repository.findAll();
        List<ClientDTO> listDTO = listEntidade.stream().map(x -> new ClientDTO(x)).toList();
        return listDTO ;

    }


    public ClientDTO create(ClientDTO entity) {
        Client client = new Client(entity);

        repository.save(client);

        ClientDTO clientDTO = new ClientDTO(client);

        return clientDTO;

    }

    public ClientDTO findById(String id) {

        Client client =  repository.findById(id).get();

        ClientDTO clientDTO = new ClientDTO(client);

        return clientDTO;
    }

    public ClientDTO update(String id , ClientDTO clientDTO){

        Client entity = repository.findById(id).get();

        entity.setName(clientDTO.getName());
        entity.setCel(clientDTO.getCel());
        entity.setEmail(clientDTO.getEmail());
        entity.setCpf(clientDTO.getCpf());

        repository.save(entity);

        ClientDTO newClientDTO = new ClientDTO(entity);

        return newClientDTO;
    }

    public void delete(String id) {
        Client entity  = repository.findById(id).get();
        repository.delete(entity);

    }
}
