package com.portmz.portsystem.model.charge;

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
@RequestMapping("/api/v1/charge")
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    @Autowired
    private ChargeRepository chargeRepository;

    @GetMapping
    public List<ChargeDto> findAllCharges() {
        return chargeService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> saveCharge(@Valid @RequestBody ChargeDto dto) {
        dto = chargeService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getChargeById(@PathVariable Long id) {
        Optional<Charge> chargeOptional = Optional.ofNullable(chargeService.getChargeById(id));
        return ResponseEntity.status(HttpStatus.OK).body(chargeOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCharge(@PathVariable Long id, @RequestBody ChargeDto dto) {
        Optional<Charge> chargeOptional = Optional.ofNullable(chargeService.getChargeById(id));
        Charge charge = chargeService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(charge);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteChargeById(@PathVariable Long id) {
        Optional<Charge> chargeOptional = Optional.ofNullable(chargeService.getChargeById(id));
        chargeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Charge deleted successfully");
    }
}
