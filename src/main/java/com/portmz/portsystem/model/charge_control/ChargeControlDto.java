package com.portmz.portsystem.model.charge_control;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portmz.portsystem.model.charge.Charge;
import com.portmz.portsystem.model.container.Container;
import com.portmz.portsystem.model.ship.Ship;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeControlDto {

    private Long id;
    private Ship ship;
    private Long ship_id;
    private Container container;
    private Long container_id;
    private Charge charge;
    private Long charge_id;
    private String origin;
    private String destiny;

    public ChargeControlDto (ChargeControl dto){

        id = dto.getId();
        ship = dto.getShip();
        ship_id = dto.getShip().getId();
        container = dto.getContainer();
        container_id = dto.getContainer().getId();
        charge = dto.getCharge();
        charge_id = dto.getCharge().getId();
        origin = dto.getOrigin();
        destiny = dto.getDestiny();

    }
}
