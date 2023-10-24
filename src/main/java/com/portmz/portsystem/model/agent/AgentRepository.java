package com.portmz.portsystem.model.agent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    List<AgentDto> findAgentById(Long agentId);

    boolean existsByContact(String contact);
}
