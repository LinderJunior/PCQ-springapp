package com.portmz.portsystem.model.client;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<ClientDto> findAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable Long id) {
        Optional<Client> clientOptional = Optional.ofNullable(clientService.getClientById(id));
        return ResponseEntity.status(HttpStatus.OK).body(clientOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveClient(@Valid @RequestBody ClientDto dto) {
        dto = clientService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClientById(@PathVariable Long id) {
        Optional<Client> clientOptional = Optional.ofNullable(clientService.getClientById(id));
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable Long id, @RequestBody ClientDto dto) {
        Optional<Client> clientOptional = Optional.ofNullable(clientService.getClientById(id));
        Client client = clientService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }
}
