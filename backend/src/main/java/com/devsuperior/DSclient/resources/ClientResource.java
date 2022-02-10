package com.devsuperior.DSclient.resources;

import com.devsuperior.DSclient.dto.ClientDTO;
import com.devsuperior.DSclient.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> finAll() {
        List<ClientDTO> dto = service.findAll();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> finById(@PathVariable Long id) {
        ClientDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto) {
        ClientDTO client = service.insert(dto);
        return ResponseEntity.ok().body(client);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO dto, @PathVariable Long id) {
        ClientDTO client = service.update(dto, id);
        return ResponseEntity.ok().body(client);
    }

    @DeleteMapping (value = "/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
