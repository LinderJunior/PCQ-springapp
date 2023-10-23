package com.portmz.portsystem.model.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;

    @Size(max=60)
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateBirth;

    @Size(max=255)
    @Email
    private String email;

    @Size(max=60)
    private String numberID;

    @Size(max=15)
    private String gender;

    private String company;

    private String cell;

    public ClientDto(Client entityClient) {
        id = entityClient.getId();
        name = entityClient.getName();
        dateBirth = entityClient.getDateBirth();
        email = entityClient.getEmail();
        numberID = entityClient.getNumberID();
        gender = entityClient.getGender();
        company = entityClient.getCompany();
        cell = entityClient.getCell();
    }
}
