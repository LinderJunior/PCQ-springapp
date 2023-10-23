package com.portmz.portsystem.model.employee;

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
public class EmployeeDto {

    private Long id;

    //@NotBlank
    @Size(max=60)
    private String firstName;

    //@NotBlank
    @Size(max=60)
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateBirth;

    //@NotBlank
    @Size(max=15)
    private String gender;

    //@NotBlank
    @Email
    @Size(max=255)
    private String email;

   private String cell;


    public EmployeeDto(Employee entityEmployee) {
         id = entityEmployee.getId();
         firstName = entityEmployee.getFirstName();
         lastName = entityEmployee.getLastName();
         gender = entityEmployee.getGender();
         email = entityEmployee.getEmail();
         cell = entityEmployee.getCell();
         dateBirth = entityEmployee.getDateBirth();
    }


}