package com.portmz.portsystem.model.ship;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShipRepository extends JpaRepository<Ship, Long> {

    List<ShipDto> findShipById(Long shipId);
}
