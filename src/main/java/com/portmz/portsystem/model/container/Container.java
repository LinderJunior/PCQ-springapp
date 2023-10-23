package com.portmz.portsystem.model.container;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "container")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;
    private int capacity;

    @Size(max = 255)
    private String description;

    @Size(max = 60)
    private String color;

    @Size(max = 60)
    private String state;
}
