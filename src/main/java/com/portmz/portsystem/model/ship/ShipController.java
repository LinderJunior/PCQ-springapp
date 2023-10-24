package com.portmz.portsystem.model.ship;

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
@RequestMapping("/api/v1/ship")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @Autowired
    private ShipRepository shipRepository;

    @GetMapping
    public List<ShipDto> findAllShips() {
        return shipService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> saveShip(@Valid @RequestBody ShipDto dto) {
        dto = shipService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getShipById(@PathVariable Long id) {
        Optional<Ship> shipOptional = Optional.ofNullable(shipService.getShipById(id));
        return ResponseEntity.status(HttpStatus.OK).body(shipOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateShip(@PathVariable Long id, @RequestBody ShipDto dto) {
        Optional<Ship> shipOptional = Optional.ofNullable(shipService.getShipById(id));
        Ship ship = shipService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(ship);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteShipById(@PathVariable Long id) {
        Optional<Ship> shipOptional = Optional.ofNullable(shipService.getShipById(id));
        shipService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Ship deleted successfully");
    }
}
