package com.portmz.portsystem.model.ship;

import java.util.List;

public interface ShipService {

    List<ShipDto> findAll();

    Ship getShipById(Long id);

    ShipDto save(ShipDto dto);

    void delete(Long id);

    Ship update(Long id, ShipDto dto);
}
