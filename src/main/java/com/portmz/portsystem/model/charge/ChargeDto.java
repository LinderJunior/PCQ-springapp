package com.portmz.portsystem.model.charge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeDto {

    private Long id;
    private String type;
    private String origin;
    private String destiny;
    private String description;

    public ChargeDto(Charge entityCharge) {
        id = entityCharge.getId();
        type = entityCharge.getType();
        origin = entityCharge.getOrigin();
        destiny = entityCharge.getDestiny();
        description = entityCharge.getDescription();
    }
}
