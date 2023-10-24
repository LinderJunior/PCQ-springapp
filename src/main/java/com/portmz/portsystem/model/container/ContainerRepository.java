package com.portmz.portsystem.model.container;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContainerRepository extends JpaRepository<Container, Long> {

    List<ContainerDto> findContainerById(Long containerId);

    boolean existsByNumber(int number);
}
