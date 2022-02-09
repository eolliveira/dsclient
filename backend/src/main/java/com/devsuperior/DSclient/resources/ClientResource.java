package com.devsuperior.DSclient.resources;

import com.devsuperior.DSclient.dto.ClientDTO;
import com.devsuperior.DSclient.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> finAll() {
        List<ClientDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }



}
