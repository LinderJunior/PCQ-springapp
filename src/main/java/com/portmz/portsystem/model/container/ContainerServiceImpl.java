package com.portmz.portsystem.model.container;

import com.portmz.portsystem.exceptionHandler.BusinesException;
import com.portmz.portsystem.model.user.User;
import com.portmz.portsystem.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    private UserService userService;

    @Autowired
    private ContainerRepository containerRepository;

    @Override
    public List<ContainerDto> findAll() {
        List<Container> list = containerRepository.findAll();
        return list.stream().map(ContainerDto::new).collect(Collectors.toList());
    }

    @Override
    public Container getContainerById(Long id) {
        return containerRepository.findById(id).orElseThrow(() -> new BusinesException("Container with ID " + id + " not found"));
    }

    @Override
    public ContainerDto save(ContainerDto dto) {
        User userSpringSecurity = userService.authenticated();
        if (userSpringSecurity == null) {
            throw new RuntimeException("Usuário não autenticado.");
        }
        // Verifique se o objeto user tem authorities antes de acessá-las
        if (userSpringSecurity.getAuthorities() == null) {
            throw new RuntimeException("O usuário não possui autorizações.");
        }

        Container container = new Container();

        // Verificar se o número de contêiner já existe no banco de dados
        if (containerRepository.existsByNumber(dto.getNumber())) {
            throw new BusinesException("Container with the same number already exists");
        }

        copyDtoToEntityInsert(container, dto);
        container = containerRepository.save(container);
        return new ContainerDto(container);
    }

    @Override
    public void delete(Long id) {
        try {
            containerRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Container update(Long id, ContainerDto dto) {
        Optional<Container> optionalContainer = containerRepository.findById(id);
        if (optionalContainer.isPresent()) {
            Container container = optionalContainer.get();
            copyDtoToEntityInsert(container, dto);
            return containerRepository.save(container);
        }
        return null;
    }

    private void copyDtoToEntityInsert(Container container, ContainerDto dto) {
        container.setNumber(dto.getNumber());
        container.setCapacity(dto.getCapacity());
        container.setDescription(dto.getDescription());
        container.setColor(dto.getColor());
        container.setState(dto.getState());
    }
}
