package com.portmz.portsystem.model.charge;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="charge")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Charge {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String type;

        //@NotBlank
        @Size(max=60)
        private String origin;

        private String destiny;

        //@NotBlank
        @Size(max=60)
        private String description;





}
