package com.devsuperior.DSclient.services;

import com.devsuperior.DSclient.dto.ClientDTO;
import com.devsuperior.DSclient.entities.Client;
import com.devsuperior.DSclient.repositories.ClientRepository;
import com.devsuperior.DSclient.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional
    public List<ClientDTO> findAll() {
        List<Client> clients = repository.findAll();
        return clients.stream().map(c -> new ClientDTO(c)).collect(Collectors.toList());
    }


    @Transactional
    public ClientDTO findById(Long id) {
        Optional<Client> client = repository.findById(id);
        Client c = client.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new ClientDTO(c);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();
        dtoForEntity(dto, client);
        client = repository.save(client);
        return new ClientDTO(client);

    }

    @Transactional
    public ClientDTO update(ClientDTO dto, Long id) {
        Client client = repository.getOne(id);
        dtoForEntity(dto, client);
        client = repository.save(client);
        return new ClientDTO(client);

    }

    @Transactional
    public void delete(Long id) {
        Client client = repository.getOne(id);
        repository.deleteById(client.getId());
    }

    private void dtoForEntity(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }
}
