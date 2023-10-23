package com.portmz.portsystem.model.client;

import java.util.List;

public interface ClientService {

    List<ClientDto> findAll();

    Client getClientById(Long id);

    ClientDto save(ClientDto dto);

    void delete(Long id);

    Client update(Long id, ClientDto dto);
}
