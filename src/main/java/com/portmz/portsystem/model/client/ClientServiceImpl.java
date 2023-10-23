package com.portmz.portsystem.model.client;

import com.portmz.portsystem.exceptionHandler.BusinesException;
import com.portmz.portsystem.model.user.User;
import com.portmz.portsystem.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<ClientDto> findAll() {
        List<Client> list = clientRepository.findAll();
        return list.stream().map(ClientDto::new).collect(Collectors.toList());
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new BusinesException("Client with ID " + id + " not found"));
    }

    @Override
    public ClientDto save(ClientDto dto) {

        User userSpringSecurity = userService.authenticated();
        if (userSpringSecurity == null) {
            throw new RuntimeException("Usuário não autenticado.");
        }
        // Verifique se o objeto user tem authorities antes de acessá-las
        if (userSpringSecurity.getAuthorities() == null) {
            throw new RuntimeException("O usuário não possui autorizações.");
        }

        Client client = new Client();

        if (clientRepository.existsByEmail(dto.getEmail())) {
            throw new BusinesException("An email with the same name already exists INSERT ");
        }
        copyDtoToEntityInsert(client, dto);
        client = clientRepository.save(client);
        return new ClientDto(client);
    }

    @Override
    public void delete(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client update(Long id, ClientDto dto) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            if (!client.getEmail().equals(dto.getEmail()) && clientRepository.existsByEmail(dto.getEmail())) {
                throw new BusinesException("An email with the same name already exists UPDATE ");
            }
            copyDtoToEntityInsert(client, dto);
            return clientRepository.save(client);
        }
        return null;
    }

    private void copyDtoToEntityInsert(Client client, ClientDto dto) {
        client.setCell(dto.getCell());
        client.setEmail(dto.getEmail());
        client.setName(dto.getName());
        client.setNumberID(dto.getNumberID());
        client.setGender(dto.getGender());
        client.setDateBirth(dto.getDateBirth());
        client.setCompany(dto.getCompany());
    }
}
