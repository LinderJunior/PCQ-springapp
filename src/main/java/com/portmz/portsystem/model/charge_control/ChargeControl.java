package com.portmz.portsystem.model.charge_control;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portmz.portsystem.model.charge.Charge;
import com.portmz.portsystem.model.container.Container;
import com.portmz.portsystem.model.ship.Ship;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="charge_control")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ship_id", nullable = false)
    private Ship ship;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "container_id", nullable = false)
    private Container container;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "charge_id", nullable = false)
    private Charge charge;


    private String origin;

    private String destiny;




}
