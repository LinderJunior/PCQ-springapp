package com.portmz.portsystem.model.terminalload;

import java.util.List;

public interface TerminalLoadService {

    List<TerminalLoadDto> findAll();

    TerminalLoad getTerminalLoadById(Long id);

    TerminalLoadDto save(TerminalLoadDto dto);

    void delete(Long id);

    TerminalLoad update(Long id, TerminalLoadDto dto);
}
