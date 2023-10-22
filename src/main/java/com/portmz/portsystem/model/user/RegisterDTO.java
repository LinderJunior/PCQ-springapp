package com.portmz.portsystem.model.user;

import java.time.LocalDate;

//public record RegisterDTO(String username, String password, UserRole role, String firstName, String lastName, String email, LocalDate dateBirth, String gender, String cell) {

    public record RegisterDTO(String username, String password, UserRole role, String firstName, String lastName, LocalDate dateBirth, String gender, String cell, String email) {



    }





