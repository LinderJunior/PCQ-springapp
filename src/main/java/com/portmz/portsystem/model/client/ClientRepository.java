package com.portmz.portsystem.model.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByEmail(String email);

    List<ClientDto> findClientById(Long clientId);
}
