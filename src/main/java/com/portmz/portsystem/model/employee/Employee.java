package com.portmz.portsystem.model.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank
    @Size(max=60)
    private String firstName;

    //@NotBlank
    @Size(max=60)
    private String lastName;

    //@NotBlank
    @Email
    @Size(max=255)
    private String email;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateBirth;

    //@NotBlank
    @Size(max=15)
    private String gender;

    private String cell;

}