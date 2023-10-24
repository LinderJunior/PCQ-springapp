package com.portmz.portsystem.model.ship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipDto {

    private Long id;
    private String name;
    private String model;
    private String quiet;
    private String size;

    public ShipDto(Ship entityShip) {
        id = entityShip.getId();
        name = entityShip.getName();
        model = entityShip.getModel();
        quiet = entityShip.getQuiet();
        size = entityShip.getSize();
    }
}
