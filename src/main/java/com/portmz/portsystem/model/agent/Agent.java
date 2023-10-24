package com.portmz.portsystem.model.agent;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name="agent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank
    @Size(max=60)
    private String name;

    //@NotBlank
    @Size(max=60)
    private String province;

    private String contact;

}
