package com.portmz.portsystem.model.agent;

import java.util.List;

public interface AgentService {

    List<AgentDto> findAll();

    Agent getAgentById(Long id);

    AgentDto save(AgentDto dto);

    void delete(Long id);

    Agent update(Long id, AgentDto dto);
}
