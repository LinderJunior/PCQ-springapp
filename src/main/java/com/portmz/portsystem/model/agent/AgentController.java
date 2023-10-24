package com.portmz.portsystem.model.agent;

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
@RequestMapping("/api/v1/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentRepository agentRepository;

    @GetMapping
    public List<AgentDto> findAllAgents() {
        return agentService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> saveAgent(@Valid @RequestBody AgentDto dto) {
        dto = agentService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAgentById(@PathVariable Long id) {
        Optional<Agent> agentOptional = Optional.ofNullable(agentService.getAgentById(id));
        return ResponseEntity.status(HttpStatus.OK).body(agentOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAgent(@PathVariable Long id, @RequestBody AgentDto dto) {
        Optional<Agent> agentOptional = Optional.ofNullable(agentService.getAgentById(id));
        Agent agent = agentService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(agent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAgentById(@PathVariable Long id) {
        Optional<Agent> agentOptional = Optional.ofNullable(agentService.getAgentById(id));
        agentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Agent deleted successfully");
    }
}
