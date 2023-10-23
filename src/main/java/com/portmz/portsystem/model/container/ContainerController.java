package com.portmz.portsystem.model.container;

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
@RequestMapping("/api/v1/container")
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    @Autowired
    private ContainerRepository containerRepository;

    @GetMapping
    public List<ContainerDto> findAllContainers() {
        return containerService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> saveContainer(@Valid @RequestBody ContainerDto dto) {
        dto = containerService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getContainerById(@PathVariable Long id) {
        Optional<Container> containerOptional = Optional.ofNullable(containerService.getContainerById(id));
        return ResponseEntity.status(HttpStatus.OK).body(containerOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContainer(@PathVariable Long id, @RequestBody ContainerDto dto) {
        Optional<Container> containerOptional = Optional.ofNullable(containerService.getContainerById(id));
        Container container = containerService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(container);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContainerById(@PathVariable Long id) {
        Optional<Container> containerOptional = Optional.ofNullable(containerService.getContainerById(id));
        containerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Container deleted successfully");
    }
}
