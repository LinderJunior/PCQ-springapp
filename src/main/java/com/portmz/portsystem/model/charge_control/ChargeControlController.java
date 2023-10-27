package com.portmz.portsystem.model.charge_control;

import com.portmz.portsystem.model.product.Product;
import com.portmz.portsystem.model.product.ProductDto;
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
@RequestMapping("/api/v1/chargeControl")
public class ChargeControlController {

    @Autowired
    private ChargeControlRepository chargeControlRepository;

    @Autowired
    private ChargeControlService chargeControlService;


    @GetMapping
    public List<ChargeControlDto> findAllChargeControl() {
        return chargeControlService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> saveChargeControl(@Valid @RequestBody ChargeControlDto dto) {
        dto = chargeControlService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateChargeControl(@PathVariable Long id, @RequestBody ChargeControlDto dto) {
        Optional<ChargeControl> productOptional = Optional.ofNullable(chargeControlService.getChargeControlById(id));
        ChargeControl chargeControl = chargeControlService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(chargeControl);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getChargeControlById(@PathVariable Long id) {
        Optional<ChargeControl> chargeControlOptional = Optional.ofNullable(chargeControlService.getChargeControlById(id));
        return ResponseEntity.status(HttpStatus.OK).body(chargeControlOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteChargeControlById(@PathVariable Long id) {
        Optional<ChargeControl> chargeControlOptional = Optional.ofNullable(chargeControlService.getChargeControlById(id));
        chargeControlService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Charge Control deleted successfully");
    }

}
