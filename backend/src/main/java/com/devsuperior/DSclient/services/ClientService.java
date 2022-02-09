package com.devsuperior.DSclient.services;

import com.devsuperior.DSclient.dto.ClientDTO;
import com.devsuperior.DSclient.entities.Client;
import com.devsuperior.DSclient.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;


    public List<ClientDTO> findAll() {
        List<Client> clients = repository.findAll();
        return clients.stream().map(c -> new ClientDTO(c)).collect(Collectors.toList());
    }


}
