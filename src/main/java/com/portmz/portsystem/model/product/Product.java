package com.portmz.portsystem.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portmz.portsystem.model.client.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank
    @Size(max=60)
    private String name;

    //@NotBlank
    @Size(max=60)
    private String type;

    //Manager
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;



}
