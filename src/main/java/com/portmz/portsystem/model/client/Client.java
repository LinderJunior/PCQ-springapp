package com.portmz.portsystem.model.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank
    @Size(max=60)
    private String name;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateBirth;

    //@NotBlank
    @Email
    @Size(max=255)
    private String email;

    //@NotBlank
    @Size(max=60)
    private String numberID;

    //@NotBlank
    @Size(max=15)
    private String gender;

    private String company;

    private String cell;

}
