package com.devsuperior.DSclient.services;

import com.devsuperior.DSclient.dto.ClientDTO;
import com.devsuperior.DSclient.entities.Client;
import com.devsuperior.DSclient.repositories.ClientRepository;
import com.devsuperior.DSclient.services.exceptions.DatabaseException;
import com.devsuperior.DSclient.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable page) {
        Page<Client> clients = repository.findAll(page);
        return clients.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> client = repository.findById(id);
        Client c = client.orElseThrow(() -> new ResourceNotFoundException("Resource id: " + id + " not found"));
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
        try{
            Client client = repository.getOne(id);
            dtoForEntity(dto, client);
            client = repository.save(client);
            return new ClientDTO(client);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Client id:" + id + " not found");
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            Client client = repository.getOne(id);
            repository.deleteById(client.getId());
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Client id:" + id + " not found");
        }
        catch (EmptyResultDataAccessException e) {
            throw new DatabaseException("Database integrity violation");
        }
    }

    private void dtoForEntity(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }
}
