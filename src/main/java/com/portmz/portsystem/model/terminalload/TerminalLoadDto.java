package com.portmz.portsystem.model.terminalload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerminalLoadDto {

    private Long id;
    private String size;
    private String priority;
    private String state;

    public TerminalLoadDto(TerminalLoad entityTerminalLoad) {
        id = entityTerminalLoad.getId();
        size = entityTerminalLoad.getSize();
        priority = entityTerminalLoad.getPriority();
        state = entityTerminalLoad.getState();
    }
}
