package com.portmz.portsystem.model.container;

import java.util.List;

public interface ContainerService {

    List<ContainerDto> findAll();

    Container getContainerById(Long id);

    ContainerDto save(ContainerDto dto);

    void delete(Long id);

    Container update(Long id, ContainerDto dto);
}
