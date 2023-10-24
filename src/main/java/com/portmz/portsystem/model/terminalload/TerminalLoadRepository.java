package com.portmz.portsystem.model.terminalload;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminalLoadRepository extends JpaRepository<TerminalLoad, Long> {

    List<TerminalLoadDto> findTerminalLoadById(Long terminalLoadId);
}
