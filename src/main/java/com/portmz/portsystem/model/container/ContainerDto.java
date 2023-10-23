package com.portmz.portsystem.model.container;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContainerDto {

    private Long id;
    private int number;
    private int capacity;
    private String description;
    private String color;
    private String state;

    public ContainerDto(Container entityContainer) {
        id = entityContainer.getId();
        number = entityContainer.getNumber();
        capacity = entityContainer.getCapacity();
        description = entityContainer.getDescription();
        color = entityContainer.getColor();
        state = entityContainer.getState();
    }
}
