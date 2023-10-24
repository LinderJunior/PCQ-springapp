package com.portmz.portsystem.model.terminalload;

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
@RequestMapping("/api/v1/terminalload")
public class TerminalLoadController {

    @Autowired
    private TerminalLoadService terminalLoadService;

    @Autowired
    private TerminalLoadRepository terminalLoadRepository;

    @GetMapping
    public List<TerminalLoadDto> findAllTerminalLoads() {
        return terminalLoadService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> saveTerminalLoad(@Valid @RequestBody TerminalLoadDto dto) {
        dto = terminalLoadService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTerminalLoadById(@PathVariable Long id) {
        Optional<TerminalLoad> terminalLoadOptional = Optional.ofNullable(terminalLoadService.getTerminalLoadById(id));
        return ResponseEntity.status(HttpStatus.OK).body(terminalLoadOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTerminalLoad(@PathVariable Long id, @RequestBody TerminalLoadDto dto) {
        Optional<TerminalLoad> terminalLoadOptional = Optional.ofNullable(terminalLoadService.getTerminalLoadById(id));
        TerminalLoad terminalLoad = terminalLoadService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(terminalLoad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTerminalLoadById(@PathVariable Long id) {
        Optional<TerminalLoad> terminalLoadOptional = Optional.ofNullable(terminalLoadService.getTerminalLoadById(id));
        terminalLoadService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Terminal Load deleted successfully");
    }
}
