package com.portmz.portsystem.model.terminalload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "terminal_load")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerminalLoad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 60)
    private String size;

    @Size(max = 60)
    private String priority;

    @Size(max = 60)
    private String state;
}
